/*
 * Course: CS1021 - FIXME
 * Winter FIXME
 * Lab 4 - Inheritance with Shapes
 * Name: FIXME
 * Created: FIXME
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 * This class draws a face out of a bunch of rectangles.
 * @author taylor
 * @version 20191216
 */
public class FaceMaker extends Application {
    public static final int WINDOW_SIZE = 800;
    public static final int GRID_INCREMENT = WINDOW_SIZE/10;
    public static final int HEAD_SIZE = 700;

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
        WinPlotterFX plotter = new WinPlotterFX();
        plotter.setWindowTitle("Face Maker");
        plotter.setWindowSize(WINDOW_SIZE, WINDOW_SIZE);
        plotter.setBackgroundColor(Color.BLACK.getRed(), Color.BLACK.getGreen(),
                Color.BLACK.getBlue());
        final boolean showGrid = true;
        plotter.setGrid(showGrid, GRID_INCREMENT, GRID_INCREMENT, Color.GRAY);
        Shape head = createHead();
        Shape leftEye = createLeftEye();
        Shape rightEye = createRightEye();
        Shape nose = createNose();
        Shape mouth = createMouth();

        head.draw(plotter);
        leftEye.draw(plotter);
        rightEye.draw(plotter);
        nose.draw(plotter);
        mouth.draw(plotter);

        plotter.showPlotter();
    }

    /**
     * Creates and returns a shape representing the head
     * @return shape representing the head
     */
    private Shape createHead() {
        final int xLeft = (WINDOW_SIZE-HEAD_SIZE)/2;
        final int yBottom = (WINDOW_SIZE-HEAD_SIZE)/2;
        return new Rectangle(xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE);
    }

    /**
     * Creates and returns a shape representing the right eye
     * @return shape representing the right eye
     */
    private Shape createRightEye() {
        final double scaleFactor = 0.15;
        final double size = scaleFactor*HEAD_SIZE;
        final double yBottom = WINDOW_SIZE/2 + size*3/2;
        final double xLeft = WINDOW_SIZE/2 + size;
        return new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
    }

    /**
     * Creates and returns a shape representing the left eye
     * @return shape representing the left eye
     */
    private Shape createLeftEye() {
        final double scaleFactor = 0.15;
        final double size = scaleFactor*HEAD_SIZE;
        final double yBottom = WINDOW_SIZE/2 + size*3/2;
        final double xLeft = WINDOW_SIZE/2 - size*2;
        return new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
    }

    /**
     * Creates and returns a shape representing the nose
     * @return shape representing the nose
     */
    private Shape createNose() {
        final double scaleFactor = 0.2;
        final double size = scaleFactor*HEAD_SIZE;
        final double xLeft = WINDOW_SIZE/2 - size/2;
        final double yBottom = (WINDOW_SIZE)/2;
        return new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
    }

    /**
     * Creates and returns a shape representing the mouth
     * @return shape representing the mouth
     */
    private Shape createMouth() {
        final double scaleFactor = 0.3;
        final double size = scaleFactor*HEAD_SIZE;
        final double xLeft = WINDOW_SIZE/2 - size/2;
        final double yBottom = (WINDOW_SIZE)/2 - size*3/2;
        return new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
    }

}
