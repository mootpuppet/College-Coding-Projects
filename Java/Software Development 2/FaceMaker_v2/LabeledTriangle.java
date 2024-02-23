/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class LabeledTriangle
 * Name: mottc
 * Created 1/10/2023
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * LabeledTriangle purpose: child class of Triangle. A labeled Triangle object
 *                          that can be instantiated to draw features of a face
 *                          on a WinPlotterFX object
 *
 * @author mottc
 * @version created on 1/10/2023 at 9:13 PM
 */
public class LabeledTriangle extends Triangle {

    private final String name;

    /**
     * constructs LabeledTriangle Object
     * @param x - x coordinate of origin point
     * @param y - y coordinate of origin point
     * @param base - length of base
     * @param height - length of height
     * @param color - color of this object
     * @param name - name of this object
     */

    public LabeledTriangle(double x, double y, double base, double height, Color color, String name)
    throws  IllegalArgumentException{
        super(x, y, base, height, color);
        this.name=name;
    }//end LabeledTriangle

    /**
     * draws this object and prints name at origin point
     * @param plotter - WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);
       plotter.printAt(x,y,name);
    }//end draw

}//end LabeledTriangle