package com.komisariat.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

import com.komisariat.MainControllers.App;
import com.komisariat.UI.controllers.LoginController;
import com.komisariat.UI.controllers.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    }

    @Override
    public void showCommissionerUI() {
        System.out.println("komisarz zalogowany");
        try {
            SceneManager.switchFXML(primaryStage, "officerMain");
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
        return null;
    }

    @Override
    public Map<String, String> getEditedOfficerInfo() {
        return null;
    }

    @Override
    public Integer getRemoveOfficerID() {
        return null;
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
        return null;
    }

    @Override
    public Map<String, String> getEndShiftInfo() {
        return null;
    }

    public Collection<Map<String, String>> getOfficerInfo() {
        return app.getOfficers();
    }

    public void startShift(){
        app.startShift();
    }

    public void finishShift(){
        app.finishShift();
    }
}
