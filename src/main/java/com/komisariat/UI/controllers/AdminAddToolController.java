package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class AdminAddToolController {


    public TextField manufacturer;
    public TextField model;
    public TextField category;
    private GraphicalUI ui = GraphicalUI.getInstance();
    private static AdminAddToolController instance;

    public AdminAddToolController() {
        instance = this;
    }

    public static AdminAddToolController getInstance() {
        return instance;
    }

    public void addButtonHandler(ActionEvent event) {
        if(manufacturer.getText().equals("") || model.getText().equals("") || category.getText().equals("")) {
            ui.showErrorMessage("Wszystkie pola powinny być wypełnione!");
            return;
        }
        Map<String, String> toolInfo = new HashMap<>();
        toolInfo.put("manufacturer", manufacturer.getText());
        toolInfo.put("model", model.getText());
        toolInfo.put("category", category.getText());
        AdminKitInfoController.getInstance().addTool(toolInfo);
        cancelButtonHandler(event);
    }

    public void cancelButtonHandler(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        AdminUIController.getInstance().refreshTables();
    }
}
