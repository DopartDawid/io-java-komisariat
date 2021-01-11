package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AdminUIController {

    public TableView<Map<String, String>> officerTable;
    public TableView<Map<String, String>> kitTable;
    public Button addOfficerButton;
    public Button editOfficerButton;
    public Button deleteOfficerButton;
    public Button addKitButton;
    public Button editKitButton;
    public Button deleteKitButton;

    private GraphicalUI ui = GraphicalUI.getInstance();
    private static AdminUIController instance;

    public AdminUIController() {
        instance = this;
    }

    @FXML
    private void initialize() throws Exception {
        officerTable.getColumns().get(0).setCellValueFactory(new MapValueFactory("badgeNumber"));
        officerTable.getColumns().get(1).setCellValueFactory(new MapValueFactory("firstName"));
        officerTable.getColumns().get(2).setCellValueFactory(new MapValueFactory("lastName"));
        officerTable.getColumns().get(3).setCellValueFactory(new MapValueFactory("hqAddress"));
        officerTable.getColumns().get(4).setCellValueFactory(new MapValueFactory("rank"));

        officerTable.setItems(generateDataInMap(ui.getOfficerInfo()));
    }

    public static AdminUIController getInstance() {
        return instance;
    }

    private ObservableList<Map<String, String>> generateDataInMap(Collection<Map<String, String>> maps) {
        ObservableList<Map<String, String>> data = FXCollections.observableArrayList();

        for (Map<String, String> officerInfo: maps
             ) {
            data.add(officerInfo);
        }

        return data;
    }
}
