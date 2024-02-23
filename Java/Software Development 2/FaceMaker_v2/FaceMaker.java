/*
 * Course: CS1021 - 021
 * Winter 2022-2023
 * Lab 4 - Inheritance with Shapes
 * Name: Mottc
 * Created: 1/4/2023
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

/**
 * This class draws a face out of a bunch of rectangles.
 * @author taylor
 * @version 20191216
 */
public class FaceMaker extends Application {
    public static final int WINDOW_SIZE = 800;
    public static final int GRID_INCREMENT = WINDOW_SIZE/10;
    public static final int HEAD_SIZE = 700;
    public static Random generator = new Random();

    /**
     * Launches the JavaFX application
     * @param args ignored
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Use the Shape class and its descendants to draw a face.
     * @param stage Default stage given to a JavaFX program. Unused.
     */
    @Override
    public void start(Stage stage) {

        int shapeChoice = whichShape();

        WinPlotterFX plotter = new WinPlotterFX();
        plotter.setWindowTitle("Face Maker");
        plotter.setWindowSize(WINDOW_SIZE, WINDOW_SIZE);
        plotter.setBackgroundColor(Color.BLACK.getRed(), Color.BLACK.getGreen(),
                Color.BLACK.getBlue());
        final boolean showGrid = true;
        plotter.setGrid(showGrid, GRID_INCREMENT, GRID_INCREMENT, Color.GRAY);

        Shape head = createHead(shapeChoice);
        Shape leftEye = createLeftEye(shapeChoice);
        Shape rightEye = createRightEye(shapeChoice);
        Shape nose = createNose(shapeChoice);
        Shape mouth = createMouth(shapeChoice);

        head.draw(plotter);
        leftEye.draw(plotter);
        rightEye.draw(plotter);
        nose.draw(plotter);
        mouth.draw(plotter);

        plotter.showPlotter();

    }//end start

    /**
     * Creates and returns a shape representing the head
     * @return shape representing the head
     */
    private Shape createHead(int shapeChoice) {

        String name="head";
        Shape shape;

        if(shapeChoice<1 || shapeChoice>5){
            shapeChoice=randomShape();
        }//end if

        final int xLeft = (WINDOW_SIZE - HEAD_SIZE) / 2;
        final int yBottom = (WINDOW_SIZE - HEAD_SIZE) / 2;

        if(shapeChoice==1) {
            shape = new Rectangle(xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE);
        }//end if

        else if(shapeChoice==2){
            final int xCenter = WINDOW_SIZE/2;
            final int yCenter = WINDOW_SIZE/2;
            shape = new Circle(xCenter,yCenter,HEAD_SIZE/2,Color.WHITE);
        }//end else if

        else if(shapeChoice==3){
            shape = new Triangle(xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE);
        }//end else if

        else if(shapeChoice==4){
            shape= new LabeledRectangle(xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE,name);
        }//end else if

        else{
            shape = new LabeledTriangle(xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE,name);
        }//end else

            return shape;

    }//end createHead

    /**
     * Creates and returns a shape representing the right eye
     * @return shape representing the right eye
     */
    private Shape createRightEye(int shapeChoice) {

        String name="right eye";
        Shape shape;

        if(shapeChoice<1 || shapeChoice>5){
            shapeChoice=randomShape();
        }//end if

        final double scaleFactor = 0.15;
        final double size = scaleFactor * HEAD_SIZE;
        final double yBottom = WINDOW_SIZE / 2 + size * 3 / 2;
        final double xLeft = WINDOW_SIZE / 2 + size;

        if(shapeChoice==1) {

            shape = new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
        }//end if

        else if(shapeChoice==2){
            final double yCenter = WINDOW_SIZE/2 + size*2;
            final double xCenter = WINDOW_SIZE/2 + size*3/2;
            shape = new Circle(xCenter,yCenter,size/2,Color.WHITE);
        }//end else if

        else if(shapeChoice==3){
            shape = new Triangle(xLeft, yBottom, size, size, Color.WHITE);
        }//end else if

        else if(shapeChoice==4){
            shape= new LabeledRectangle(xLeft, yBottom, size, size, Color.WHITE,name);
        }//end else if

        else{
            shape = new LabeledTriangle(xLeft, yBottom, size, size, Color.WHITE,name);
        }//end else

        return shape;

    }//end createRightEye

    /**
     * Creates and returns a shape representing the left eye
     * @return shape representing the left eye
     */
    private Shape createLeftEye(int shapeChoice) {

        Shape shape;
        String name="left eye";

        if(shapeChoice<1 || shapeChoice>5){
            shapeChoice=randomShape();
        }//end if

        final double scaleFactor = 0.15;
        final double size = scaleFactor * HEAD_SIZE;
        final double yBottom = WINDOW_SIZE / 2 + size * 3 / 2;
        final double xLeft = WINDOW_SIZE / 2 - size * 2;

        if(shapeChoice==1) {
            shape = new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
        }//end if

        else if(shapeChoice==2){
            final double yCenter = WINDOW_SIZE/2 + size*2;
            final double xCenter = WINDOW_SIZE/2 - size*3/2;
            shape = new Circle(xCenter,yCenter,size/2,Color.WHITE);
        }//end else if

        else if(shapeChoice==3){
            shape = new Triangle(xLeft, yBottom, size, size, Color.WHITE);
        }//end else if

        else if(shapeChoice==4){
            shape= new LabeledRectangle(xLeft, yBottom, size, size, Color.WHITE,name);
        }//end else if

        else{
            shape = new LabeledTriangle(xLeft, yBottom, size, size, Color.WHITE,name);
        }//end else

        return shape;

    }//end createLeftEye

    /**
     * Creates and returns a shape representing the nose
     * @return shape representing the nose
     */
    private Shape createNose(int shapeChoice) {

        String name="nose";
        Shape shape;

        if(shapeChoice<1 || shapeChoice>5){
            shapeChoice=randomShape();
        }//end if

        final double scaleFactor = 0.2;
        final double size = scaleFactor*HEAD_SIZE;
        final double xLeft = WINDOW_SIZE / 2 - size / 2;
        final double yBottom = (WINDOW_SIZE) / 2;

        if(shapeChoice==1) {

            shape= new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
        }//end if

        else if(shapeChoice==2){
            final double yCenter = WINDOW_SIZE/2+size/2;
            final double xCenter = WINDOW_SIZE/2;
            shape = new Circle(xCenter,yCenter,size/2,Color.WHITE);
        }//end else if

        else if(shapeChoice==3){
            shape = new Triangle(xLeft, yBottom, size, size, Color.WHITE);
        }//end else if

        else if(shapeChoice==4){
            shape= new LabeledRectangle(xLeft, yBottom, size, size, Color.WHITE,name);
        }//end else if

        else{
            shape = new LabeledTriangle(xLeft, yBottom, size, size, Color.WHITE,name);
        }//end else

        return shape;

    }//end createNose

    /**
     * Creates and returns a shape representing the mouth
     * @return shape representing the mouth
     */
    private Shape createMouth(int shapeChoice) {

        String name="mouth";
        Shape shape;

        if(shapeChoice<1 || shapeChoice>5){
            shapeChoice=randomShape();
        }//end if

        final double scaleFactor = 0.3;
        final double size = scaleFactor*HEAD_SIZE;
        final double xLeft = WINDOW_SIZE / 2 - size / 2;
        final double yBottom = (WINDOW_SIZE) / 2 - size * 3 / 2;

        if(shapeChoice==1) {

            shape= new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
        }//end if

        else if(shapeChoice==2) {
            final double yCenter = WINDOW_SIZE/2-size;
            final double xCenter = WINDOW_SIZE/2;
            shape = new Circle(xCenter,yCenter,size/2,Color.WHITE);
        }//end else if

        else if(shapeChoice==3){
            shape = new Triangle(xLeft, yBottom, size, size, Color.WHITE);
        }//end else if

        else if(shapeChoice==4){
            shape= new LabeledRectangle(xLeft, yBottom, size, size, Color.WHITE,name);
        }//end else if

        else{
            shape = new LabeledTriangle(xLeft, yBottom, size, size, Color.WHITE,name);
        }//end else

        return shape;

    }//end createMouth

    /**
     * Asks the user which shape they would like to use returns an int
     * @return - int shapeChoice
     */
    private static int whichShape(){
        Scanner in = new Scanner(System.in);

        int shapeChoice=-1;


            System.out.println("Which shape would you like to use to draw a face? \n" +
                    "\t[1] Rectangle\n" +
                    "\t[2] Circle\n" +
                    "\t[3] Triangle\n" +
                    "\t[4] Labeled Rectangle\n" +
                    "\t[5] Labeled Triangle\n" +
                    "\t[6] Random");

            shapeChoice = in.nextInt();



        return shapeChoice;

    }//end whichShape

    /**
     * generates a random number between 1 and 5 (inclusive)
     * @return - number between 1 and 5
     */
    private static int randomShape(){

       return (int)(generator.nextDouble()*5+1);
    }//end randomShape

}//end FaceMaker
