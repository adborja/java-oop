package com.edu.cedesistemas.oop.model.geometry;

public class Rectangle implements Shape{

    private Point bl;
    private Point tr;
    private Point br;
    private Point tl;
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public Point getBl() {
        return bl;
    }

    public Point getBr() {
        return br;
    }

    public Point getTl() {
        return tl;
    }

    public Point getTr() {
        return tr;
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
