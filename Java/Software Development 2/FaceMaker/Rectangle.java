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
 * Rectangle purpose: FIXME
 *
 * @author mottc
 * @version created on 1/4/2023 at 9:43 AM
 */
public class Rectangle extends Shape{

    protected final double height;
    protected final double width;

    public Rectangle(double x, double y, double width, double height, Color color){

        super(x, y, color);

       this.height=height;
       this.width=width;

    }

    public void draw(WinPlotterFX plotter){

    }

}