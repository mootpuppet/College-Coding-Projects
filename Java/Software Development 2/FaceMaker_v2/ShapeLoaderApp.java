/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class ShapeLoaderApp
 * Name: mottc
 * Created 1/25/2023
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 * Course CS1021-021
 * Winter 2022-2023
 * ShapeLoaderApp purpose: Load shapes from a txt file and draw them using WinPlotterFX
 *
 * @author mottc
 * @version created on 1/25/2023 at 8:24 AM
 */
public class ShapeLoaderApp extends Application {

    private List<Shape> shapes = new ArrayList<>();
    private Scanner in;
    WinPlotterFX plotter = new WinPlotterFX();


    /**
     * calls javafx launch method
     * @param args - arguments, String array
     */
    public static void main(String[] args) {

        launch(args);
    }//end main

    /**
     * uses FileChooser to choose a text file to load up shapes from
     * calls the readShapes method, and the drawShapes method
     * shows an alert if an exception is caught and will loop back
     * to the file chooser.
     * shows the plotter window
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        boolean flag = false;

        do {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Shape File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            try {
                readShapes(selectedFile.toPath());
            } catch (InputMismatchException e) {
                flag=true;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("File Content Error");
                alert.setContentText(e.getMessage());

            } //end try catch

            drawShapes();

        }while(flag);

        plotter.showPlotter();

    }//end start

    /**
     * calls the draw method for each shape
     */
    private void drawShapes() {
        for(Shape shape: shapes){
            shape.draw(plotter);
        }//end for
    }//end drawShapes

    /**
     * uses the Scanner class to read the chosen txt file
     * sets plotter title and size. calls stringToColor to set
     * the plotter background color and calls parse shape to
     * add shape objects to the Shape ArrayList.
     *
     * @param path - chosen file
     * @throws InputMismatchException - thrown if the path leads to an incorrect file type,
     *                                  line 2 can't be parsed into doubles, or received from
     *                                  stringToColor or parseShape methods
     *
     */
    public void readShapes(Path path) throws InputMismatchException{


        try {
            in=new Scanner(path);
        } catch (IOException e) {
            throw new InputMismatchException("Incorrect File Type");
        }//end try catch

        plotter.setWindowTitle(in.nextLine());

        try {
            plotter.setWindowSize(in.nextDouble(), in.nextDouble());
        }catch (NumberFormatException e){
            throw new InputMismatchException("Invalid input on line 2");
        }//end try catch

        in.nextLine(); //clear buffer


        Color backgroundColor = stringToColor(in.nextLine());

        plotter.setBackgroundColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue());


        while (in.hasNextLine()) {

            shapes.add(parseShape(in.nextLine()));
        }//end while

        in.close();

    }//end readShapes

    /**
     * takes a color code String in hex format and parses it into a Color Object.
     * splits the colorHex string into 3 different Strings, representing the hex
     * values for red, green, and blue, then calls hexToColor to convert that hex
     * in to a double between 0.0 - 1.0
     * @param colorHex - color code as a String in hex format
     * @return - Color Object
     * @throws InputMismatchException - thrown if String is in the incorrect format
     */
    private static Color stringToColor(String colorHex) throws InputMismatchException {

        Character firstChar =colorHex.charAt(0);

        if(!firstChar.equals('#') || colorHex.length()!=7){
            throw new InputMismatchException("Color Hex Code Incorrect format");
        }//end if

        else {

            String redHex = "" + colorHex.charAt(1) + colorHex.charAt(2);
            String greenHex = "" + colorHex.charAt(3) + colorHex.charAt(4);
            String blueHex = "" + colorHex.charAt(5) + colorHex.charAt(6);

            try {
                return new Color(hexToColorVal(redHex), hexToColorVal(greenHex), hexToColorVal(blueHex), 1);
            } catch (NumberFormatException e) {

                throw new InputMismatchException("Color Hex Code Incorrect format");
            }//end try catch
        }//end else


    }//end stringToColor

    /**
     * converts a String in hex formant into a double between 0.0 - 1.0
     * @param hexNum - String in hex format
     * @return - returns a Double that is between 0.0 - 1.0
     * @throws NumberFormatException - thrown if input isn't in hex format
     */
    private static Double hexToColorVal(String hexNum) throws NumberFormatException{



        return Integer.parseInt(hexNum,16)/255.0;

    }//end hexToColorVal

    /**
     * Converts a String into a Shape Object. Uses helper methods to parse
     * specific shapes. Returns a Shape Object
     * @param shapeAsString - Shape in String format
     * @return - Shape Object
     * @throws InputMismatchException - thrown if received from helper methods
     *                                  or the first letter of the shapeAsString
     *                                  String does not match any parsable Shapes
     */
    private static Shape parseShape(String shapeAsString) throws InputMismatchException {



        Shape shape = null;
        String[] splitShapeString = shapeAsString.split("\s");


        if (splitShapeString[0].equalsIgnoreCase("p:")) {
            shape = parsePoint(splitShapeString);

        } else if (splitShapeString[0].equalsIgnoreCase("c:")) {
            shape = parseCircle(splitShapeString);

        } else if (splitShapeString[0].equalsIgnoreCase("t:")) {
            shape = parseTriangle(splitShapeString);


        } else if (splitShapeString[0].equalsIgnoreCase("r:")) {
            shape = parseRectangle(splitShapeString);
        }
        else if (splitShapeString[0].equalsIgnoreCase("lt:")) {
            shape = parseLabeledTriangle(splitShapeString);
        }
        else if (splitShapeString[0].equalsIgnoreCase("lr:")) {
            shape = parseLabeledRectangle(splitShapeString);
        }
        else{
            throw new InputMismatchException("Incorrect Shape format\n"+
                    splitShapeString[0]+" is not a shape");
        }



        return shape;
    }//end parseShape

    /**
     * parses a LabeledRectangle Object from a String Array
     * @param splitShapeString - String[]
     * @return - LabeledRectangle Object
     * @throws InputMismatchException - thrown if received from LabeledRectangle() constructor
     */
    private static LabeledRectangle parseLabeledRectangle(String[] splitShapeString) throws InputMismatchException {
        LabeledRectangle labeledRectangle;
        String name="";
        try {
            for(int i = 6; i< splitShapeString.length; i++){

                name += splitShapeString[i]+"\s";

            }//end for

            labeledRectangle = new LabeledRectangle(Double.parseDouble(splitShapeString[1]),
                    Double.parseDouble(splitShapeString[2]),Double.parseDouble(splitShapeString[4]),
                    Double.parseDouble(splitShapeString[5]), stringToColor(splitShapeString[3]),
                    name);

        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }//end try catch
        return labeledRectangle;
    }//end parseLabeledRectangle

    /**
     * parses a LabeledTriangle Object from a String Array
     * @param splitShapeString - String[]
     * @return - LabeledTriangle Object
     * @throws InputMismatchException - thrown if received from LabeledTriangle() constructor
     */
    private static LabeledTriangle parseLabeledTriangle(String[] splitShapeString) throws InputMismatchException {
        LabeledTriangle labeledTriangle;
        String name="";
        try {
            for(int i = 6; i< splitShapeString.length; i++){

                name += splitShapeString[i]+"\s";

            }

            labeledTriangle = new LabeledTriangle(Double.parseDouble(splitShapeString[1]),
                    Double.parseDouble(splitShapeString[2]),Double.parseDouble(splitShapeString[4]),
                    Double.parseDouble(splitShapeString[5]), stringToColor(splitShapeString[3]),
                    name);

        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
        return labeledTriangle;
    }//end parseLabeledTriangle

    /**
     * parses a Rectangle Object from a String Array
     * @param splitShapeString - String[]
     * @return - Rectangle Object
     * @throws InputMismatchException - thrown if received from Rectangle() constructor
     */
    private static Rectangle parseRectangle(String[] splitShapeString) {
        Rectangle rectangle;
        try {
            rectangle = new Rectangle(Double.parseDouble(splitShapeString[1]),
                    Double.parseDouble(splitShapeString[2]),Double.parseDouble(splitShapeString[4]),
                    Double.parseDouble(splitShapeString[5]), stringToColor(splitShapeString[3]));

        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
        return rectangle;
    }//end parseRectangle

    /**
     * parses a Triangle Object from a String Array
     * @param splitShapeString - String[]
     * @return - Triangle Object
     * @throws InputMismatchException - thrown if received from Triangle() constructor
     */

    private static Triangle parseTriangle(String[] splitShapeString) {
        Triangle triangle;
        try {
            triangle = new Triangle(Double.parseDouble(splitShapeString[1]),
                    Double.parseDouble(splitShapeString[2]), Double.parseDouble(splitShapeString[4]),
                    Double.parseDouble(splitShapeString[5]), stringToColor(splitShapeString[3]));

        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
        return triangle;
    }//end parseTriangle

    /**
     * parses a Circle Object from a String Array
     * @param splitShapeString - String[]
     * @return - Circle Object
     * @throws InputMismatchException - thrown if received from Circle() constructor
     */
    private static Circle parseCircle(String[] splitShapeString) {
        Circle circle;
        try {
            circle = new Circle(Double.parseDouble(splitShapeString[1]),
                    Double.parseDouble(splitShapeString[2]),Double.parseDouble(splitShapeString[4]),
                    stringToColor(splitShapeString[3]));

        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
        return circle;
    }//end parseCircle

    /**
     * parses a Point Object from a String Array
     * @param splitShapeString - String[]
     * @return - Point Object
     * @throws InputMismatchException - thrown if received from Point() constructor
     */
    private static Point parsePoint(String[] splitShapeString) {
        Point point;
        try {
            point = new Point(Double.parseDouble(splitShapeString[1]),
                    Double.parseDouble(splitShapeString[2]), stringToColor(splitShapeString[3]));

        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
        return point;
    }//end parsePoint


}//end ShapeLoaderApp