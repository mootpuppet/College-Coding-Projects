/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class ImageManipulator
 * Name: mottc
 * Created 2/7/2023
 */
package mottc;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * ImageManipulator purpose: loads the ImageManipulator GUI
 *
 * @author mottc
 * @version created on 2/7/2023 at 9:41 AM
 */
public class ImageManipulator extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * loads ImageManipulator fxml and shows the GUI
     * @param mainStage
     * @throws Exception
     */
    @Override
    public void start(Stage mainStage) throws Exception {

        FXMLLoader mainLoader = new FXMLLoader();
        Parent root = mainLoader.load(Objects.requireNonNull(getClass()
                .getResource("ImageManipulator.fxml")).openStream());
        Scene scene = new Scene(root);
        mainStage.setTitle("Image Manipulator");
        mainStage.setScene(scene);
        mainStage.show();

        ImageManipulatorController mainController= mainLoader.getController();
        mainController.setMainGrid((Pane)root);


        FXMLLoader filterLoader = new FXMLLoader();

        Parent secondaryRoot = filterLoader.load(Objects.requireNonNull(getClass()
                .getResource("filter.fxml")).openStream());

        Stage secondaryStage= new Stage();

        FilterController filterController = filterLoader.getController();
        secondaryStage.setTitle("Filter");
        secondaryStage.setScene(new Scene(secondaryRoot));
        secondaryStage.hide();

        mainController.setSecondaryStage(secondaryStage);


        filterController.setMainStage(mainStage);
        filterController.setMainController(mainController);

    }


}