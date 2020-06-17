package com.edu.cedesistemas.oop.model.geometry;

public class Rectangle implements  Shape {

    private Point bl;
    private Point tr;
    private Point br;
    private Point tl;
    private double height;
    private double width;

    public Rectangle (Point a, double b, double c){
        height = b;
        width = c;
    }

    public Point getToRight(){
        return tr;
    }

    public Point getBottomLeft(){
        return bl;
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
        return getHeight() * getWidth();
    }

    @Override
    public double perimeter() {
        return 2 * (getHeight() + getWidth());
    }
}
