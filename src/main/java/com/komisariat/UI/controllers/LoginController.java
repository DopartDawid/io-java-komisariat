package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class LoginController {

    @FXML
    public TextField l_input;

    @FXML
    public PasswordField p_input;

    private GraphicalUI ui = GraphicalUI.getInstance();
    private static LoginController instance;

    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        ui.startUI();
    }
}
