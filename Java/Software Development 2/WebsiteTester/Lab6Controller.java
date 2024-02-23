/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Lab6Controller
 * Name: mottc
 * Created 1/24/2023
 */
package mottc;

import edu.msoe.se1021.Lab6.WebsiteTester;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Lab6Controller purpose: Controls the Website Tester GUI
 *
 * @author mottc
 * @version created on 1/24/2023 at 7:44 AM
 */
public class Lab6Controller implements Initializable {
    @FXML
    private TextField hostDisplay;
    @FXML
    private TextField urlTxtF;
    @FXML
    private TextField fileSizeDisplay;
    @FXML
    private TextField downloadTimeDisplay;
    @FXML
    private TextField portDisplay;
    @FXML
    private TextField timeoutTxtF;
    @FXML
    private TextArea contentDisplay;
    private WebsiteTester webTester;
    private String DEFAULT_TIMEOUT;


    /**
     * handler for the analyze button. Opens a connection with the with
     * website in the URL TextField, and downloads text from the website.
     * displays the port, download time, and file size in the respective
     * TextFields, and displays the website content in the large text area.
     * @param actionEvent - analyze button pressed
     */
    @FXML
    private void analyzeURL(ActionEvent actionEvent) {

        try {
            webTester.openURL(urlTxtF.getText());
            webTester.openConnection();
            webTester.downloadText();

        }catch (MalformedURLException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("URL Error");
            alert.setContentText("The URL entered in the text box is invalid");
            alert.showAndWait();

        }catch (UnknownHostException hostException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Host Error");
            alert.setContentText("Unknown Host");
            alert.showAndWait();

        }catch (SocketTimeoutException socketException) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Timeout Dialog");
            alert.setHeaderText("Wait Longer?");
            alert.setContentText("There has been a timeout reaching the site. Click OK to extend\n" +
                    "the timeout period?");
            alert.showAndWait();
            TextInputDialog txtIn = new TextInputDialog("10");
            setTimeoutFromTextInputDialog(txtIn);

        }catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText("Error: File not found on server,\n" +
                    urlTxtF.getText());
            alert.showAndWait();
        }//end catch

        portDisplay.setText(""+webTester.getPort());
        hostDisplay.setText(webTester.getHostname());
        fileSizeDisplay.setText(webTester.getSize()+"");
        downloadTimeDisplay.setText(webTester.getDownloadTime()+"");
        contentDisplay.setText(webTester.getContent());

    }//end analyzeURL

    /**
     * sets the timeout from the TextInputDialog TextField. An error message is generated
     * if the timeout value is less than zero or if the input is not a numeric value, and
     * the TextInputDialog window is opened again.
     * @param txtIn - TextInputDialog
     */
    private void setTimeoutFromTextInputDialog(TextInputDialog txtIn) {

        TextField txtInTextField = txtIn.getEditor();

        boolean flag=false;

        do {
            txtIn.showAndWait();
            try {
                if (Integer.parseInt(txtInTextField.getText()) < 0) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error Dialog");
                    alert1.setHeaderText("Invalid Timeout Error");
                    alert1.setContentText("Timeout must be greater than or equal to 0");
                    alert1.showAndWait();
                    flag=true;

                }//end if

                else {
                    webTester.setTimeout(txtInTextField.getText());
                }//end else

            } catch (NumberFormatException e) {

                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Invalid Timeout Error");
                alert1.setContentText("Timeout must be a numeric value");
                alert1.showAndWait();
                flag=true;

            }//end catch

        }while(flag);

    }//end setTimeoutFromTextInputDialog

    /**
     * handler for the set button. Sets the website tester timeout
     * @param actionEvent - set button pressed
     */
    @FXML
    private void setTimeout(ActionEvent actionEvent) {


        try {
            if(Integer.parseInt(timeoutTxtF.getText())<0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Invalid Timeout Error");
                alert.setContentText("Timeout must be greater than or equal to 0");
                alert.showAndWait();
                timeoutTxtF.setText(DEFAULT_TIMEOUT);

            }else{
                webTester.setTimeout(timeoutTxtF.getText());
            }//end else

        }catch (NumberFormatException e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid Timeout Error");
            alert.setContentText("Timeout must be a numeric value");
            alert.showAndWait();
            timeoutTxtF.setText(DEFAULT_TIMEOUT);
        }//end catch

    }//end setTimeout

    /**
     * initializes Lab6Controller. calls WebsiteTester to instantiate a WebsiteTester object
     * and sets the default value for the timeout TextField.
     * @param url - unused
     * @param resourceBundle - unused
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webTester = new WebsiteTester();
        DEFAULT_TIMEOUT=webTester.getTimeout();
        timeoutTxtF.setText(DEFAULT_TIMEOUT);


    }//end initialize

}//end Lab6Controller