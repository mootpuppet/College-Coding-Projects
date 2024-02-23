/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Triangle
 * Name: mottc
 * Created 1/10/2023
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Triangle purpose: child class of Shape. A Triangle object
 *                   that can be instantiated to draw features of a face
 *                   on a WinPlotterFX object
 *
 * @author mottc
 * @version created on 1/10/2023 at 8:46 PM
 */
public class Triangle extends Shape{

    protected final double base;
    protected final double height;

    /**
     * constructs Triangle object
     * @param x - x coordinate of origin point
     * @param y - y coordinate of origin point
     * @param base - length of base
     * @param height - length of height
     * @param color - color of this object
     */
    public Triangle(double x, double y, double base, double height, Color color) throws IllegalArgumentException{

        super(x, y, color);

        this.base=base;
        this.height=height;

    }//end Triangle

    /**
     * draws a triangle using WinPlotterFX
     * @param plotter - WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter){
        setPenColor(plotter);
        plotter.moveTo(x,y);
        plotter.drawTo(x+base,y);
        plotter.drawTo(x+base/2,y+height);
        plotter.drawTo(x,y);
    }//end draw

}//end Triangle