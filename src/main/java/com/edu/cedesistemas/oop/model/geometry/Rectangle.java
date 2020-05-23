package com.edu.cedesistemas.oop.model.geometry;

import javax.swing.plaf.IconUIResource;
import java.awt.*;

public class Rectangle implements Shape {
    private Point bl;
    private Point tr;
    private Point br;
    private Point tl;
    private double height;
    private double width;
    public Rectangle(Point bl, double height, double width) {

    }
    public Point getTopRight(){
        return bl;
    }

    public Point getBottomLeft(){
        return bl;
    }

    public Point getBottomRight(){
        return bl;
    }

    public Point getTopLeft(){
        return bl;
    }

    public double getHeight(){
        return 0;
    }

    public double getWidth(){
        return 0;
    }

    @Override
    public double area() {
        return Math.PI * height * width;
    }

    @Override
    public double perimeter() {
        return (2 * Math.PI * Math.sqrt((Math.pow(height, 2) + Math.pow(width, 2)) / 2));
    }
}
