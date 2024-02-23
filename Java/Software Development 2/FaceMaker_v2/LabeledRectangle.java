/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class LabeledRectangle
 * Name: mottc
 * Created 1/10/2023
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * LabeledRectangle purpose: child class of Rectangle. A labeled Rectangle object
 *                           that can be instantiated to draw features of a face
 *                           on a WinPlotterFX object
 *
 * @author mottc
 * @version created on 1/10/2023 at 9:29 PM
 */
public class LabeledRectangle extends Rectangle {
    private final String name;


    /**
     * constructs a LabeledRectangle object
     * @param x - x coordinate of origin point
     * @param y - y coordinate of origin point
     * @param base
     * @param height
     * @param color
     * @param name
     */
    public LabeledRectangle(double x, double y, double base, double height, Color color, String name) throws IllegalArgumentException{
        super(x, y, base, height, color);
        this.name=name;
    }//end LabeledRectangle

    /**
     * draws this object and prints name at origin point
     * @param plotter - WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);
        plotter.printAt(x,y,name);
    }//end draw

}//end LabeledRectangle