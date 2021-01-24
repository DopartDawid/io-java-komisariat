package com.komisariat.UI.controllers;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.time.LocalDate;


public class DatePopup {


    public static LocalDate display()
    {
        Stage popupwindow=new Stage();
        LocalDate date = null;

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Choose a date");


        Button button1= new Button("Close this pop up window");


        button1.setOnAction(e -> popupwindow.close());
        final DatePicker datePicker = new DatePicker();

        datePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = datePicker.getValue();
                System.err.println("Selected date: " + date);
            }
        });


        VBox layout= new VBox(10);


        layout.getChildren().addAll(datePicker, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

        date = datePicker.getValue();

        return date;

    }

}