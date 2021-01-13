package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OfficerStartShiftInfoController {
    public Button okButton;
    public Button cancelButton;
    public Button toolInfoButton;
    public TableView<Map<String, String>> kitTable;
    public TableView<Map<String, String>> vehicleTable;
    public TableView<Map<String, String>> regionTable;
    public TableView<Map<String, String>> toolTable;


    private GraphicalUI ui = GraphicalUI.getInstance();
    private static OfficerStartShiftInfoController instance;

    public OfficerStartShiftInfoController() {
        instance = this;
    }
    public static OfficerStartShiftInfoController getInstance() {
        return instance;
    }

    public void initialize(){
        kitTable.getColumns().get(0).setCellValueFactory(new MapValueFactory("name"));
        kitTable.getColumns().get(1).setCellValueFactory(new MapValueFactory("category"));
        kitTable.getColumns().get(2).setCellValueFactory(new MapValueFactory("hqAddress"));

        vehicleTable.getColumns().get(0).setCellValueFactory(new MapValueFactory("manufacturer"));
        vehicleTable.getColumns().get(1).setCellValueFactory(new MapValueFactory("model"));
        vehicleTable.getColumns().get(2).setCellValueFactory(new MapValueFactory("vin"));

        regionTable.getColumns().get(0).setCellValueFactory(new MapValueFactory("id"));
        regionTable.getColumns().get(1).setCellValueFactory(new MapValueFactory("danger"));
        regionTable.getColumns().get(2).setCellValueFactory(new MapValueFactory("streets"));


        refreshTables();
    }



    @FXML
    public void okButtonHandler(ActionEvent event){
        if(kitTable.getSelectionModel().getSelectedItem() == null
                || vehicleTable.getSelectionModel().getSelectedItem() == null
                || regionTable.getSelectionModel().getSelectedItem() == null){
            ui.showErrorMessage("Podaj wszystkie dane słuzby !!!");
            return;
        }
        ui.getApp().startShift();
        cancelButtonHandler(event);
    }

    @FXML
    public void cancelButtonHandler(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void toolInfoButtonHandler(ActionEvent event){
        if(kitTable.getSelectionModel().getSelectedItem() == null){
            ui.showErrorMessage("Wybierz zestaw do obejrzenia !");
            return;
        }
        toolTable.getColumns().get(0).setCellValueFactory(new MapValueFactory("model"));
        toolTable.getColumns().get(1).setCellValueFactory(new MapValueFactory("manufacturer"));
        toolTable.getColumns().get(2).setCellValueFactory(new MapValueFactory("category"));
        int kitID = Integer.parseInt(kitTable.getSelectionModel().getSelectedItem().get("id"));
        toolTable.setItems(generateDataInMap(ui.getApp().getKitTools(kitID)));
    }


    private ObservableList<Map<String, String>> generateDataInMap(Collection<Map<String, String>> maps) {
        ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
        data.addAll(maps);

        return data;
    }

    public void refreshTables() {
        kitTable.setItems(generateDataInMap(ui.getApp().getKits()));
        vehicleTable.setItems(generateDataInMap(ui.getApp().getVehicles()));
        regionTable.setItems(generateDataInMap(ui.getApp().getRegions()));
    }

    public Map<String, String> getNewShiftInfo(){
        Map<String, String> info = new HashMap<>();
        info.put("kitID", kitTable.getSelectionModel().getSelectedItem().get("id"));
        info.put("vehicleID", vehicleTable.getSelectionModel().getSelectedItem().get("vin"));
        info.put("regionID", regionTable.getSelectionModel().getSelectedItem().get("id"));

        return info;
    }
}
