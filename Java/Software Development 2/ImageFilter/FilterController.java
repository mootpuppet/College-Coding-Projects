/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class FilterController
 * Name: mottc
 * Created 2/13/2023
 */
package mottc;

import edu.msoe.cs1021.ImageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * FilterController purpose: Controls the filter GUI
 *
 * @author mottc
 * @version created on 2/13/2023 at 2:34 PM
 */
public class FilterController implements Initializable {


    @FXML
    private Button applyBtn;
    @FXML
    private TextField tF00;
    @FXML
    private TextField tF01;
    @FXML
    private TextField tF02;
    @FXML
    private TextField tF10;
    @FXML
    private TextField tF11;
    @FXML
    private TextField tF12;
    @FXML
    private TextField tF20;
    @FXML
    private TextField tF21;
    @FXML
    private TextField tF22;

    private ArrayList<TextField> textFields;

    private Stage mainStage;
    private ImageManipulatorController mainController;

    /**
     * sets main stage
     * @param mainStage
     */
    public void setMainStage(Stage mainStage) {this.mainStage=mainStage;}

    /**
     * sets main controller
     * @param mainController
     */
    public void setMainController(ImageManipulatorController mainController) {
        this.mainController = mainController;
    }

    /**
     * filter values for blurring and image
     * @param actionEvent
     */
    @FXML
    private void blur(ActionEvent actionEvent) {
        tF00.setText("0");
        tF01.setText("1");
        tF02.setText("0");
        tF10.setText("1");
        tF11.setText("5");
        tF12.setText("1");
        tF20.setText("0");
        tF21.setText("1");
        tF22.setText("0");
    }

    /**
     * filter values for sharpening an image
     * @param actionEvent
     */
    @FXML
    private void sharpen(ActionEvent actionEvent) {
        tF00.setText("0");
        tF01.setText("-1");
        tF02.setText("0");
        tF10.setText("-1");
        tF11.setText("5");
        tF12.setText("-1");
        tF20.setText("0");
        tF21.setText("-1");
        tF22.setText("0");
    }

    /**
     * applies filter values in textfields
     * @param actionEvent
     */
    @FXML
    private void apply(ActionEvent actionEvent) {
        double denominator=0;
        double[] kernel= new double[9];

        for(TextField tf: textFields){
            denominator+=Double.parseDouble(tf.getText());
        }
        if(denominator!=0) {
            for (int i=0; i<textFields.size(); i++) {
                kernel[i] = Double.parseDouble(textFields.get(i).getText())/denominator;
            }
            mainController.image =ImageUtil.convolve(mainController.imageView.getImage(), kernel);
            mainController.imageView.setImage(mainController.image);

        }
    }

    /**
     * initializes FilterController class. Upon initlization, the apply button is disabled,
     * an array list of text fields is created, and each text field gets a listener
     * @param url - not used
     * @param resourceBundle - not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        textFields = new ArrayList<>(Arrays.asList(tF00,tF01,tF02,tF10,tF11,tF12,tF20,tF21,tF22));

        for(TextField tf: textFields){
            tf.textProperty().addListener((obs, oldText, newText) -> update());
        }

        applyBtn.setDisable(true);

    }

    /**
     * keeps enables the apply button if all the textfields have valid inputs
     * disables the apply button if inputs are not valid and will clear a text
     * field if a non-numeric is entered. unless the entered text is "-" and it
     * is the first character
     */
    private void update(){

        int applyEnableCount=0;
        for(TextField tf: textFields){

            if(!tf.getText().equals("-")) {
                try {
                    Double.parseDouble(tf.getText());
                } catch (IllegalArgumentException e) {
                    tf.setText("");
                }
            }

            if(tf.getText().equals("") || (tf.getText().equals("-") && tf.getText().length()==1)){
                applyEnableCount+=1;
            }

                applyBtn.setDisable(applyEnableCount!=0);

        }

    }
}