package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        refreshTables();

        editOfficerButton.setDisable(true);
        deleteOfficerButton.setDisable(true);
        officerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            editOfficerButton.setDisable(false);
            deleteOfficerButton.setDisable(false);
        });

        editKitButton.setDisable(true);
        deleteKitButton.setDisable(true);
    }

    public static AdminUIController getInstance() {
        return instance;
    }

    @FXML
    public void addNewOfficerHandler(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Dodaj nowego funkcjonariusza");
            stage.setScene(SceneManager.parseFXML("adminOfficerInfo"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(event.getSource())).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editOfficerHandler(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Edytuj funkcjonariusza");
            stage.setScene(SceneManager.parseFXML("adminOfficerInfo"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(event.getSource())).getScene().getWindow());
            stage.show();
            Map<String, String> selected = officerTable.getSelectionModel().getSelectedItem();
            AdminOfficerInfoController temp = AdminOfficerInfoController.getInstance();
            temp.officerID = selected.get("id");
            temp.badgeNumber.setText(selected.get("badgeNumber"));
            temp.firstName.setText(selected.get("firstName"));
            temp.lastName.setText(selected.get("lastName"));
            temp.hq.setValue(temp.hq.getItems().stream().filter(info -> info.get("id").equals(selected.get("hqID"))).findFirst().orElse(null));
            temp.rank.setValue(temp.rank.getItems().stream().filter(rank -> rank.equals(selected.get("rank"))).findFirst().orElse(""));
            temp.addButton.setText("Edytuj");
            temp.addButton.onActionProperty().setValue(event1 -> temp.editButtonHandler(event1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteOfficerHandler(ActionEvent event) {
        Map<String, String> temp = officerTable.getSelectionModel().getSelectedItem();
        Alert alert =
                new Alert(Alert.AlertType.WARNING,
                        "Czy jesteś pewny, że chcesz usunąć '" + temp.get("firstName") + " " + temp.get("lastName") + "?",
                        ButtonType.APPLY,
                        ButtonType.CANCEL);
        alert.setTitle("Usuwanie funkcjonariusza");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.APPLY) {
            ui.getApp().removeOfficer();
        }
        this.refreshTables();
    }

    private ObservableList<Map<String, String>> generateDataInMap(Collection<Map<String, String>> maps) {
        ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
        data.addAll(maps);

        return data;
    }

    public void refreshTables() {
        officerTable.setItems(generateDataInMap(ui.getOfficerInfo()));
    }

    public Map<String, String> getSelectedOfficer() {
        return officerTable.getSelectionModel().getSelectedItem();
    }
}
