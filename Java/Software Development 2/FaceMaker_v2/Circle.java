/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Circle
 * Name: mottc
 * Created 1/9/2023
 */
package mottc;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Circle purpose: child class of Shape. A Circle object
 *                 that can be instantiated to draw features of a face
 *                 on a WinPlotterFX object
 *
 * @author mottc
 * @version created on 1/9/2023 at 1:41 PM
 */
public class Circle extends Shape {

    private final double radius;

    /**
     * Constructs a Circle object
     * @param x - x coordinate of center point
     * @param y - y coordinate of center point
     * @param radius - radius of circle
     * @param color - color of circle
     */
    public Circle(double x, double y,double radius, Color color) throws IllegalArgumentException {


        super(x, y, color);

        this.radius=radius;

    }//end Circle

    /**
     * constructs a Circle object
     * @param x - x coordinate of bottom left corner of bounding box
     * @param y - y coordinate of bottom left corner of bounding box
     * @param width - width of bounding box
     * @param height - height of bounding box
     * @param color - color of Circle
     */
    public Circle(double x, double y, double width, double height, Color color) throws IllegalArgumentException{
        super(x+width/2, y+height/2, color);

        radius= Math.sqrt(Math.pow(width/2,2)+Math.pow(height/2,2));


    }//end Circle

    /**
     * draws Circle on plotter
     * @param plotter - WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter){
        setPenColor(plotter);


        plotter.moveTo(x+radius*Math.cos(0),y+radius*Math.sin(0));

        double theta;

        for(int i=0; i<=360; i++){

            theta=i*(Math.PI/180); //angle in radians

            plotter.drawTo(x+radius*Math.cos(theta),y+radius*Math.sin(theta));

        }//end for


    }//end draw

}//end Circle