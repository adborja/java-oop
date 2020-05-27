package com.edu.cedesistemas.oop.model.geometry;

import java.awt.*;

public class Rectangle implements Shape {
    private Point bl;
    private Point tr;
    private Point br;
    private Point tl;
    private double height;
    private double width;

    public Rectangle (Point bl, Point tr, Point br, Point tl, double height, double width){
        this.bl = bl;
        this.tr = tr;
        this.br = br;
        this.tl = tl;
        this.height = height;
        this.width = width;
    }

    public Point getBottomLeft(){
        return bl;
    }

    public Point getTopRight(){
        return tr;
    }

    public Point getBottomRight(){
        return br;
    }

    public Point getTopLeft(){
        return tl;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }
}
