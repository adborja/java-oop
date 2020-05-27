package com.edu.cedesistemas.oop.model.geometry;

public class Rectangle<point> implements Shape {
    private point bl;
    private point tr;
    private point br;
    private point tl;
    private double height;
    private double width;

    public Rectangle(point bl, point tr, point br, point tl, double height, double width) {
        this.bl = bl;
        this.tr = tr;
        this.br = br;
        this.tl = tl;
        this.height = height;
        this.width = width;
    }

    public double getTopRight() {
        double y = 0;
        return y;
    }

    public point getBottomLeft() {
        return bl;
    }

    public point getBottomRight() {
        return tr;
    }

    public point getTopLeft() {
        return br;
    }

    public double getHeight() {
        double m = 0;
        return m;
    }

    public double getWidth() {
        double y = 0;
        return y;
    }

    @Override
    public double area() {
        double z = 0;
        return z;
    }

    @Override
    public double perimeter() {
        double p = 0;
        return p;
    }
}