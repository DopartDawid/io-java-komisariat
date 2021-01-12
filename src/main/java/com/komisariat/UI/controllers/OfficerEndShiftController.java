package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class OfficerEndShiftController {

    public TextField mileage;
    public TextField reportTitle;
    public TextArea content;
    public Button okButton;
    public Button cancelButton;

    private GraphicalUI ui = GraphicalUI.getInstance();
    private static OfficerEndShiftController instance;

    public OfficerEndShiftController() {
        instance = this;
    }

    public static OfficerEndShiftController getInstance() {
        return instance;
    }

    @FXML
    public void okButtonHandler(ActionEvent event){
        if(reportTitle.getText().equals("") || content.getText().equals("") || mileage.getText().equals("")){
            ui.showErrorMessage("Wszystkie pola powinny być wypełnione!");
            return;
        }
        ui.getApp().finishShift();
        cancelButtonHandler(event);
    }

    @FXML
    public void cancelButtonHandler(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public Map<String, String> getEndShiftInfo(){
        Map<String, String> info = new HashMap<>();
        info.put("title",reportTitle.getText());
        info.put("content", content.getText());
        info.put("endMileage", mileage.getText());

        return info;
    }
}
