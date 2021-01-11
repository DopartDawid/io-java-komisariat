package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AdminOfficerInfoController {


    public TextField badgeNumber;
    public TextField firstName;
    public TextField lastName;
    public ChoiceBox<Map<String, String>> hq;
    public ChoiceBox<String> rank;
    public Button addButton;
    public Button cancelButton;

    public String officerID;
    private GraphicalUI ui = GraphicalUI.getInstance();
    private static AdminOfficerInfoController instance;

    public AdminOfficerInfoController() {
        instance = this;
        officerID = "";
    }

    public static AdminOfficerInfoController getInstance() {
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

        Collection<String> ranks = ui.getApp().getRanks();
        rank.getItems().addAll(ranks);
    }

    public void addButtonHandler(ActionEvent event) {
        if(firstName.getText().equals("") || lastName.getText().equals("") || badgeNumber.getText().equals("")
        || hq.getValue() == null || rank.getValue() == null) {
            ui.showErrorMessage("Wszystkie pola powinny być wypełnione!");
            return;
        }
        ui.getApp().addNewOfficer();
        cancelButtonHandler(event);
    }

    public void editButtonHandler(ActionEvent event) {
        if(firstName.getText().equals("") || lastName.getText().equals("") || badgeNumber.getText().equals("")
                || hq.getValue() == null || rank.getValue() == null) {
            ui.showErrorMessage("Wszystkie pola powinny być wypełnione!");
            return;
        }
        ui.getApp().editOfficer();
        cancelButtonHandler(event);
    }

    public void cancelButtonHandler(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        AdminUIController.getInstance().refreshTables();
    }

    public Map<String, String> getNewOfficerInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("id", this.officerID);
        info.put("badgeNumber", this.badgeNumber.getText());
        info.put("firstName", this.firstName.getText());
        info.put("lastName", this.lastName.getText());
        info.put("hqID", this.hq.getValue().get("id"));
        info.put("rank", this.rank.getValue());

        return info;
    }
}
