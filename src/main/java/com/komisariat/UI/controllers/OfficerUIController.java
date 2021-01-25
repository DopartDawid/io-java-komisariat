package com.komisariat.UI.controllers;
import com.komisariat.BusinessObjects.Officer;
import com.komisariat.UI.GraphicalUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class OfficerUIController {

    public Button startShiftButton;
    public Button finishShiftButton;
    public Button signOutButton;

    private GraphicalUI ui = GraphicalUI.getInstance();
    private static OfficerUIController instance;

    public OfficerUIController() {instance = this;}

    public static OfficerUIController getInstance() { return instance; }

    @FXML
    private void handleStartShiftButtonClick(ActionEvent event){
        ui.startShift(event);
    }

    @FXML
    private void handleFinishShiftButtonClick(ActionEvent event){
        ui.finishShift(event);
    }

    @FXML
    private void handleSignOutButtonClick(ActionEvent event){
        ui.getApp().signOut();
        ui.signOut();
    }



}
