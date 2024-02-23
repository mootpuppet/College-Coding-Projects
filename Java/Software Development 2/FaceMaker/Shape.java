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
 * Shape purpose: FIXME
 *
 * @author mottc
 * @version created on 1/4/2023 at 9:42 AM
 */
public abstract class Shape {

private Color color;
protected final double x;
protected final double y;

    public Shape(double x, double y, Color color) {
        this.x= x;
        this.y=y;
        this.color=color;
    }

    public void draw(WinPlotterFX plotter) {
    }

    public void setPenColor(WinPlotterFX plotter){

    }

    public void setColor(Color color){

    }
}