package com.example.fxeventdemo;

import javafx.application.Application;
import javafx.css.converter.LadderConverter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TenisMain extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BallPane ballPane = new BallPane(); // Create a ball pane

        // Pause animation
        ballPane.pause();
        //starting animation
        ballPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                ballPane.play();
            }

        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(ballPane, 750, 650);
        primaryStage.setResizable(false);



        primaryStage.setTitle("BounceBallControl"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // Must request focus after the primary stage is displayed
        ballPane.requestFocus();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}