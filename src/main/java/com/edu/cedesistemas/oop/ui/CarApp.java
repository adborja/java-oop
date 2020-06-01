package com.edu.cedesistemas.oop.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarApp extends Application {

    public static void main(String[] args) {
        Application.launch(CarApp.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader()
                    .getResource("CarApp.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("cedesistemas car simulator");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
    }
}
