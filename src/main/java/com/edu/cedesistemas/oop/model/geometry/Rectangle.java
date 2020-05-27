package com.edu.cedesistemas.oop.model.geometry;

import java.awt.*;

public class Rectangle implements Shape{

    private  Point bl;
    private Point tr;
    private Point br;
    private  double height;
    private double width;
    public Rectangle(Point a, double height, double width){ }

    public Point getTopRight(){
        return new Point(0, 0);
    }

    public Point getBottomLeft(){
        return new Point(0, 0);
    }

    public Point getBottomRight(){
        return new Point(0, 0);
    }

    public Point getTopLeft(){
        return new Point(0, 0);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double perimeter() {
        return 0;
    }

    @Override
    public double area() {
        return 0;
    }
}
