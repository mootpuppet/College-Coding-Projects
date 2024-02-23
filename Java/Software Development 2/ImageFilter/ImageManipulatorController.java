/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class ImageManipulatorController
 * Name: mottc
 * Created 2/7/2023
 */
package mottc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * ImageManipulatorController purpose: controls the ImageManipulator GUI
 *
 * @author mottc
 * @version created on 2/7/2023 at 9:42 AM
 */
public class ImageManipulatorController implements Initializable {

    @FXML
    private Button grayscaleBtn;
    @FXML
    private Button negativeBtn;
    @FXML
    private Button redBtn;
    @FXML
    private Button showFilterBtn;
    @FXML
    private Button redGrayBtn;
    @FXML
    private Button reloadBtn;
    @FXML
    private Button saveBtn;
    private ArrayList<Button> buttons;
    private Stage secondaryStage;

    private Pane mainGrid;

    @FXML
    private Label colorLabel;
    @FXML
    protected ImageView imageView;

    private FileChooser chooser;
    private File imageFile;
    protected Image image;

    /**
     * sets the secondary stage
     * @param secondaryStage
     */
    public void setSecondaryStage(Stage secondaryStage) {this.secondaryStage = secondaryStage;}

    /**
     * Instantiates a FileChooser object
     * @param url - not used
     * @param resourceBundle - not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooser = new FileChooser();
        chooser.setTitle("Open Image File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg",
                                                                 "*.msoe", "*.bmsoe"));

        chooser.setInitialDirectory(new File("C:\\Users\\mottc\\OneDrive - Milwaukee School of Engineering" +
                "\\Desktop\\MSOE Classes\\Y2\\Y2 Q2 Winter\\Software Dev 2\\Labs\\lab8\\images"));

        buttons = new ArrayList<>(Arrays.asList(grayscaleBtn,negativeBtn,redBtn,
                                                showFilterBtn,redGrayBtn,reloadBtn, saveBtn));

    }//end initialize

    /**
     * opens image file and loads Image to image viewer
     * @param actionEvent
     */
    @FXML
    private void load(ActionEvent actionEvent) {

        imageFile= chooser.showOpenDialog(new Stage());

        try {
            image =ImageIO.read(imageFile.toPath());
            imageView.setImage(image);

            for(Button btn: buttons){
                btn.setDisable(false);
            }
        }catch (IllegalArgumentException e){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File Open Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();


        }catch (NullPointerException e){
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("File Selection Warning");
            alert.setContentText("No File Selected");
            alert.showAndWait();
        }


    }//end load

    /**
     * Saves the Image Object in the ImageViewer as an image file
     * @param actionEvent
     */
    @FXML
    private void save(ActionEvent actionEvent) {


        try {
            File savedFile = new File(chooser.showSaveDialog(new Stage()).toURI());
            ImageIO.write(savedFile.toPath(), image);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save dialog");
            alert.setHeaderText("Save Successful");
            alert.setContentText("File Path: \n"+ savedFile.toPath());
            alert.showAndWait();
        }catch(IllegalArgumentException e){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save dialog");
            alert.setHeaderText("File Save Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();


        }catch(NullPointerException e){
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("File Save Warning");
            alert.setContentText("File Was Not Saved");
            alert.showAndWait();
        }

    }

    /**
     * reloads Image Object from file path
     * @param actionEvent
     */
    @FXML
    private void reload(ActionEvent actionEvent) {

        try {
            image =ImageIO.read(imageFile.toPath());
            imageView.setImage(image);
        }catch (IllegalArgumentException e) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File Reload Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }catch (NullPointerException e){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File Reload Error");
            alert.setContentText("Path is Null");
            alert.showAndWait();

        }
    }

    /**
     * Converts Image to grayscale
     * @param actionEvent
     */
    @FXML
    private void grayscale(ActionEvent actionEvent){

        if(image!=null) {
            Transformable t = ((y, color) -> color.grayscale());

            image = transformImage(image, t);

            imageView.setImage(image);
        }
        else{
            //Create nullImage alert method
        }
    }

    /**
     * Converts Image colors to their negative counterpart
     * @param actionEvent
     */
    @FXML
    private void negative(ActionEvent actionEvent){

        Transformable t = ((y, color) -> new Color(1.0-color.getRed(),1.0-color.getGreen(),
                1.0-color.getBlue(), color.getOpacity()));

        image=transformImage(image,t);

        imageView.setImage(image);

    }

    /**
     * applies a read filter to the image in the image viewer
     * @param actionEvent
     */
    @FXML
    private void red(ActionEvent actionEvent) {


        Transformable t = ((y, color) -> new Color(color.getRed(),0,0, color.getOpacity()));

        image=transformImage(image,t);

        imageView.setImage(image);
    }

    /**
     * applies a  read-gray filter to the image in the image viewer
     * @param actionEvent
     */
    @FXML
    private void redGray(ActionEvent actionEvent) {
        Transformable t = ((y, color) ->{
            if((y%2)==0){
                return new Color(color.getRed(),0,0, color.getOpacity());
            }
            else {
                return color.grayscale();
            }
        });

        image=transformImage(image,t);

        imageView.setImage(image);
    }

    /**
     * shows/hides the filter GUI
     * @param actionEvent
     */
    @FXML
    private void showFilter(ActionEvent actionEvent) {
        if(secondaryStage.isShowing()){
            secondaryStage.hide();
        }
        else {
            Stage myStage = (Stage)mainGrid.getScene().getWindow();
            secondaryStage.setX(myStage.getX());
            secondaryStage.setY(myStage.getY()+myStage.getHeight());
            secondaryStage.setWidth(myStage.getWidth());
            secondaryStage.show();

        }
    }

    /**
     * displays the color hex code for the pixel the mouse is hovering over
     * @param mouseEvent
     */
    @FXML
    private void showColorHex(MouseEvent mouseEvent) {

        if(image != null) {
            colorLabel.setVisible(true);
            Bounds bounds = imageView.getLayoutBounds();
            int x = (int) (mouseEvent.getX() * ((image.getWidth() - 1) / (bounds.getWidth() - .5)));
            int y = (int) (mouseEvent.getY() * ((image.getHeight() - 1) / (bounds.getHeight() - .5)));

            PixelReader pixelReader = image.getPixelReader();

            colorLabel.setText(ImageIO.colorToHex(pixelReader.getColor(x, y)));
        }
        else {
            colorLabel.setVisible(false);
        }
    }

    /**
     * transforms image
     * @param image - image in image viewer
     * @param transform - a transformable function
     * @return
     */
    private static Image transformImage(Image image, Transformable transform){

        WritableImage result = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = result.getPixelWriter();
        PixelReader pixelReader = image.getPixelReader();


        for (int i = 0; i < result.getWidth(); i++) {
            for (int j = 0; j < result.getHeight(); j++) {

                pixelWriter.setColor(i, j, transform.apply(j,pixelReader.getColor(i, j)));
            }
        }


        return result;
    }

    /**
     * hides pixel hexadecimal color code when the mouse exits the imageView area
     * @param mouseEvent - mouse exits the imageView area
     */
    @FXML
    private void hideColorHex(MouseEvent mouseEvent) {
        colorLabel.setVisible(false);

    }

    /**
     * sets main grid
     * @param mainGrid
     */
    public void setMainGrid(Pane mainGrid) {this.mainGrid = mainGrid;}



}

/**
 * interface for Image color transformations
 */
@FunctionalInterface
interface Transformable{
    Color apply(int y, Color color);
}
