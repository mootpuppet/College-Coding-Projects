/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Point
 * Name: mottc
 * Created 1/25/2023
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Point purpose: a point object that can be instantiated
 *                to be drawn on a WinPlotterFX object
 *
 * @author mottc
 * @version created on 1/25/2023 at 8:32 AM
 */
public class Point extends Shape{

    /**
     * constructs Shape object
     *
     * @param x     - x coordinate of origin point
     * @param y     - y coordinate of origin point
     * @param color - color of Shape
     */
    public Point(double x, double y, Color color) throws IllegalArgumentException{
        super(x, y, color);
    }//end point

    /**
     * draws a point at (x,y) on the plotter
     * @param plotter - WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);

        plotter.drawPoint(x,y);

    }//end draw

}