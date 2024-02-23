/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Rectangle
 * Name: mottc
 * Created 1/4/2023
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Rectangle purpose: child class of Shape. A Rectangle object
 *                    that can be instantiated to draw features of a face
 *                    on a WinPlotterFX object
 *
 * @author mottc
 * @version created on 1/4/2023 at 9:43 AM
 */
public class Rectangle extends Shape{

    protected final double height;
    protected final double width;

    /**
     * constructs a Rectangle object
     * @param x - x coordinate of origin point
     * @param y - y coordinate of origin point
     * @param width - width of rectangle
     * @param height - height of rectangle
     * @param color - color of rectangle
     */
    public Rectangle(double x, double y, double width, double height, Color color) throws IllegalArgumentException{

        super(x, y, color);

        this.height=height;
        this.width=width;

    }//end Rectangle

    /**
     * draws a rectangle on plotter
     * @param plotter - WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter){

        setPenColor(plotter);
        plotter.moveTo(x,y);
        plotter.drawTo(x+width,y);
        plotter.drawTo(x+width,y+height);
        plotter.drawTo(x,y+height);
        plotter.drawTo(x,y);

    }//end draw

}//end Rectangle