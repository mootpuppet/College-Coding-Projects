/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Shape
 * Name: mottc
 * Created 1/4/2023
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Shape purpose: Parent class of Rectangle, Circle, Triangle,
 *                LabeledRectangle, and LabeledTriangle
 *
 *
 * @author mottc
 * @version created on 1/4/2023 at 9:42 AM
 */
public abstract class Shape{

    private Color color;
    protected final double x;
    protected final double y;

    /**
     * constructs Shape object
     * @param x - x coordinate of origin point
     * @param y - y coordinate of origin point
     * @param color - color of Shape
     */
    public Shape(double x, double y, Color color)throws IllegalArgumentException  {

        if(x < 0 || y < 0){
            throw new IllegalArgumentException("non-positive value entered for x or y");
        }

        this.x= x;
        this.y=y;

        setColor(color);
    }//end Shape

    /**
     * empty method. does nothing
     * @param plotter - WinPlotterFX object
     */
    public void draw(WinPlotterFX plotter) {}//end Draw

    /**
     * sets color of plotter pen
     * @param plotter - WinPlotterFX object
     */
    public void setPenColor(WinPlotterFX plotter){

        plotter.setPenColor(color.getRed(), color.getGreen(),
                color.getBlue());

    }//end setPenColor

    /**
     * sets color for Shape objects
     * @param color - color
     */
    public void setColor(Color color){
        this.color=color;
    }//end setColor

}//end Shape