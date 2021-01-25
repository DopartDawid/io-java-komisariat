package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class CommissionerUIController {

    public TableView<Map<String, String>> activeOfficerTable;
    public TableView<Map<String, String>> workSheetTable;
    public TableView<Map<String, String>> officerInfoTable;
    public TableView<Map<String, String>> availableReports;

    private GraphicalUI ui = GraphicalUI.getInstance();

    public void handleShiftStartButtonClick(ActionEvent actionEvent) {
        ui.startShift(actionEvent);
    }

    public void handleShiftEndButtonClick(ActionEvent actionEvent) {
        ui.finishShift(actionEvent);
    }

    public void handleShowReportsButtonClick(ActionEvent actionEvent) {
        Date firstDate = null;
        Date secondDate = null;
        /*TextInputDialog td = new TextInputDialog("");
        td.setHeaderText("Wprowadz poczatek okresu (format: DD/MM/RRRR lub ENTER dla wyswietlenia wszystkich):");
        Optional<String> result1 = td.showAndWait();

        if(result1.isPresent()) {
            TextInputDialog td2 = new TextInputDialog("");
            td2.setHeaderText("Wprowadz koniec okresu (format: DD/MM/RRRR lub ENTER dla wyswietlenia wszystkich):");
            Optional<String> result2 = td.showAndWait();
            if(result2.isPresent()){
                try {
                    if(!result1.get().equals("") && !result2.get().equals("")) {
                        firstDate = new SimpleDateFormat("DD/MM/YYYY").parse(result1.get());
                        secondDate = new SimpleDateFormat("DD/MM/YYYY").parse(result2.get());
                    }
                } catch (Exception e) {
                    e.printStackTrace(); //TODO - EXCEPTION HANDLING
                }
            }
        }*/

        firstDate = java.sql.Date.valueOf(DatePopup.display());
        secondDate = java.sql.Date.valueOf(DatePopup.display());
        System.out.println(firstDate + " " + secondDate);
        availableReports.getColumns().get(0).setCellValueFactory(new MapValueFactory("date"));
        availableReports.getColumns().get(1).setCellValueFactory(new MapValueFactory("title"));
        availableReports.getColumns().get(2).setCellValueFactory(new MapValueFactory("officerName"));

        availableReports.setItems(generateDataInMap(ui.getReports(firstDate, secondDate)));
    }

    public void handleShowOfficersButtonClick(ActionEvent actionEvent) {
        officerInfoTable.getColumns().get(0).setCellValueFactory(new MapValueFactory("badgeNumber"));
        officerInfoTable.getColumns().get(1).setCellValueFactory(new MapValueFactory("firstName"));
        officerInfoTable.getColumns().get(2).setCellValueFactory(new MapValueFactory("lastName"));
        officerInfoTable.getColumns().get(3).setCellValueFactory(new MapValueFactory("hqAddress"));
        officerInfoTable.getColumns().get(4).setCellValueFactory(new MapValueFactory("rank"));

        officerInfoTable.setItems(generateDataInMap(ui.getOfficerInfo()));
    }

    public void handleTimeOfWorkButtonClick(ActionEvent actionEvent) {
        Date firstDate = null;
        Date secondDate = null;
        /*TextInputDialog td = new TextInputDialog("");
        td.setHeaderText("Wprowadz poczatek okresu (format: DD/MM/RRRR lub ENTER dla wyswietlenia wszystkich):");
        Optional<String> result1 = td.showAndWait();

        if(result1.isPresent()) {
            TextInputDialog td2 = new TextInputDialog("");
            td2.setHeaderText("Wprowadz koniec okresu (format: DD/MM/RRRR lub ENTER dla wyswietlenia wszystkich):");
            Optional<String> result2 = td.showAndWait();
            if(result2.isPresent()){
                try {
                    if(!result1.get().equals("") && !result2.get().equals("")) {
                        firstDate = new SimpleDateFormat("DD/MM/YYYY").parse(result1.get());
                        secondDate = new SimpleDateFormat("DD/MM/YYYY").parse(result2.get());
                    }
                } catch (Exception e) {
                    e.printStackTrace(); //TODO - EXCEPTION HANDLING
                }
            }
        }*/
        firstDate = java.sql.Date.valueOf(DatePopup.display());
        secondDate = java.sql.Date.valueOf(DatePopup.display());
        workSheetTable.getColumns().get(1).setCellValueFactory(new MapValueFactory("begDate"));
        workSheetTable.getColumns().get(2).setCellValueFactory(new MapValueFactory("endDate"));
        workSheetTable.getColumns().get(3).setCellValueFactory(new MapValueFactory("officerInfo"));
        workSheetTable.getColumns().get(4).setCellValueFactory(new MapValueFactory("regionInfo"));
        workSheetTable.getColumns().get(5).setCellValueFactory(new MapValueFactory("kitInfo"));
        workSheetTable.getColumns().get(6).setCellValueFactory(new MapValueFactory("vehicleInfo"));


        workSheetTable.setItems(generateDataInMap(ui.getShifts(firstDate, secondDate)));
    }

    public void handleShowActiveOfficersButtonClick(ActionEvent actionEvent) {
        activeOfficerTable.getColumns().get(0).setCellValueFactory(new MapValueFactory("badgeNumber"));
        activeOfficerTable.getColumns().get(1).setCellValueFactory(new MapValueFactory("firstName"));
        activeOfficerTable.getColumns().get(2).setCellValueFactory(new MapValueFactory("lastName"));
        activeOfficerTable.getColumns().get(3).setCellValueFactory(new MapValueFactory("hqAddress"));
        activeOfficerTable.getColumns().get(4).setCellValueFactory(new MapValueFactory("rank"));

        activeOfficerTable.setItems(generateDataInMap(ui.getActiveOfficerInfo()));
    }

    private ObservableList<Map<String, String>> generateDataInMap(Collection<Map<String, String>> maps) {
        ObservableList<Map<String, String>> data = FXCollections.observableArrayList();

        for (Map<String, String> officerInfo: maps
        ) {
            data.add(officerInfo);
        }

        return data;
    }

    public void signOutButtonHandle(ActionEvent event){
        ui.getApp().signOut();
        ui.signOut();
    }
}
