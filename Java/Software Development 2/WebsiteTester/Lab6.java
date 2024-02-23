/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Lab6
 * Name: mottc
 * Created 1/18/2023
 */
package mottc;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Lab6 purpose: A Website Tester
 *
 * @author mottc
 * @version created on 1/18/2023 at 9:26 AM
 */
public class Lab6 extends Application {


    /**
     * calls launch method
     * @param args - argument String array
     */
    public static void main(String[] args) {
        launch(args);
    }//end main

    /**
     * Loads FXML file and launches the GUI
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("lab6.fxml")));

        Scene scene = new Scene(root);

        stage.setTitle("Website Tester");
        stage.setScene(scene);
        stage.show();
    }//end start

}//end Lab6