package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.util.Map;

public class AdminUIController {

    public TableView officerTable;
    public TableView kitTable;
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
        for (Map<String, String> officerInfo: ui.getOfficerInfo()
             ) {
        }
    }

    public static AdminUIController getInstance() {
        return instance;
    }
}
