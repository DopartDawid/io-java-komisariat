package com.komisariat.UI;

import java.io.IOException;
import java.util.*;

import com.komisariat.MainControllers.App;
import com.komisariat.UI.controllers.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GraphicalUI extends Application implements IUserInterface {

    private App app;
    private Stage primaryStage;
    private static GraphicalUI instance;

    public GraphicalUI() {
        instance = this;
        app = new App();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Scene scene = SceneManager.parseFXML("main");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static GraphicalUI getInstance() {
        return instance;
    }

    public App getApp() {
        return app;
    }

    @FXML
    @Override
    public void startUI() {
        try {
            app.signIn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<String> getCredentials() {
        ArrayList<String> credentials = new ArrayList<>();
        LoginController controller = LoginController.getInstance();
        credentials.add(controller.l_input.getText());
        credentials.add(controller.p_input.getText());

        return credentials;
    }

    @Override
    public void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error message");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @Override
    public void showAdminUI() {
        System.out.println("admin zalogowanyy");
        try {
            SceneManager.switchFXML(primaryStage, "adminMain");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showOfficerUI() {
        System.out.println("officer zalogowany");
        try {
            SceneManager.switchFXML(primaryStage, "officerMain");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showCommissionerUI() {
        System.out.println("komisarz zalogowany");
        try {
            SceneManager.switchFXML(primaryStage, "commissionerMain");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getKitInfo() {
        return null;
    }

    @Override
    public Collection<Map<String, String>> getToolsInfo() {
        return null;
    }

    @Override
    public Integer getRemoveKitID() {
        return null;
    }

    @Override
    public Map<String, String> getEditedKitInfo() {
        return null;
    }

    @Override
    public Collection<Map<String, String>> getEditedToolsInfo(Integer kitID) {
        return null;
    }

    @Override
    public Map<String, String> getNewOfficerInfo() {
        return AdminOfficerInfoController.getInstance().getNewOfficerInfo();
    }

    @Override
    public Map<String, String> getEditedOfficerInfo() {
        return AdminOfficerInfoController.getInstance().getNewOfficerInfo();
    }

    @Override
    public Integer getRemoveOfficerID() {
        return Integer.parseInt(AdminUIController.getInstance().getSelectedOfficer().get("id"));
    }

    @Override
    public void showReports() {

    }

    @Override
    public void showActiveOfficers() {

    }

    @Override
    public void showTimesheet() {

    }

    @Override
    public void showOfficersInfo() {

    }

    @Override
    public Map<String, String> getNewShiftInfo() {
        return OfficerStartShiftInfoController.getInstance().getNewShiftInfo();
    }

    @Override
    public Map<String, String> getEndShiftInfo() {
        return OfficerEndShiftController.getInstance().getEndShiftInfo();
    }

    public void signOut(){
        System.out.println("Wylogowano");
        try {
            SceneManager.switchFXML(primaryStage, "main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Map<String, String>> getOfficerInfo() {
        return app.getOfficers();
    }

    public Collection<Map<String, String>> getActiveOfficerInfo() {
        return app.getActiveOfficers();
    }

    public Collection<Map<String, String>> getReports(Date begDate, Date endDate) {
        return app.getReports(begDate, endDate);
    }

    public Collection<Map<String, String>> getShifts(Date begDate, Date endDate) {
        return app.getShiftsInfo(begDate, endDate);
    }

    public void startShift(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setTitle("Rozpocznij sluzbe");
            stage.setScene(SceneManager.parseFXML("officerStartShiftInfo"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(event.getSource())).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void finishShift(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setTitle("Zakoncz sluzbe");
            stage.setScene(SceneManager.parseFXML("officerEndShiftInfo"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(event.getSource())).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
