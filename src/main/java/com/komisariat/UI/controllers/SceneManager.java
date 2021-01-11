package com.komisariat.UI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SceneManager {
    public static Scene parseFXML(String fxml) throws IOException {
        FileInputStream loginFxml = new FileInputStream(new File("src/main/resources/view/" + fxml +".fxml"));
        Parent root = (new FXMLLoader()).load(loginFxml);
        return new Scene(root);
    }

    public static void switchFXML(ActionEvent event, String fxml) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(parseFXML(fxml));
        stage.show();
    }
    public static void switchScenes(ActionEvent event, String fxml, String css) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //stage.setwMaximized(true);
        Scene scene = parseFXML(fxml);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}