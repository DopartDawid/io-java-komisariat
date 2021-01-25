package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class AdminKitInfoController {


    public TextField name;
    public TextField category;
    public ChoiceBox<Map<String, String>> hq;
    public TableView<Map<String, String>> toolTable;
    private GraphicalUI ui = GraphicalUI.getInstance();
    private static AdminKitInfoController instance;

    public AdminKitInfoController() {
        instance = this;
    }

    public static AdminKitInfoController getInstance() {
        return instance;
    }

    public void initialize() {
        Collection<Map<String, String>> hqs = ui.getApp().getHeadquarters();
        hq.setConverter(new StringConverter<Map<String, String>>() {
            @Override
            public String toString(Map<String, String> info) {
                return info.get("address");
            }

            @Override
            public Map<String, String> fromString(String string) {
                return hq.getItems().stream().filter(info -> info.get("address").equals(string)).findFirst().orElse(null);
            }
        });
        hq.getItems().addAll(hqs);

        toolTable.getColumns().get(0).setCellValueFactory(new MapValueFactory("manufacturer"));
        toolTable.getColumns().get(1).setCellValueFactory(new MapValueFactory("model"));
        toolTable.getColumns().get(2).setCellValueFactory(new MapValueFactory("category"));

    }

    public void handleAddTool(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Dodaj nowe narzędzie");
            stage.setScene(SceneManager.parseFXML("adminAddTool"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(event.getSource())).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTool(Map<String, String> toolInfo) {
        toolTable.getItems().add(toolInfo);
    }
}
