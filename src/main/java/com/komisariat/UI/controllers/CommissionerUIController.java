package com.komisariat.UI.controllers;

import com.komisariat.UI.GraphicalUI;
import javafx.event.ActionEvent;

public class CommissionerUIController {

    private GraphicalUI ui = GraphicalUI.getInstance();

    public void handleShiftStartButtonClick(ActionEvent actionEvent) {
        ui.startShift();
    }

    public void handleShiftEndButtonClick(ActionEvent actionEvent) {
        ui.finishShift();
    }

    public void handleShowReportsButtonClick(ActionEvent actionEvent) {
        ui.showReports();
    }

    public void handleShowOfficersButtonClick(ActionEvent actionEvent) {
        ui.showOfficersInfo();
    }

    public void handleTimeOfWorkButtonClick(ActionEvent actionEvent) {
        ui.showTimesheet();
    }

    public void handleShowActiveOfficersButtonClick(ActionEvent actionEvent) {
        ui.showActiveOfficers();
    }
}
