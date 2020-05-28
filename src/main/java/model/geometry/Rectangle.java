package model.geometry;

import java.awt.*;

public class Rectangle implements Shape {
    private Point bl ;
    private Point tr ;
    private Point br ;
    private Point tl ;
    private double height ;
    private double width ;
    private double v1;
    private double v2;

    public double Rectangle(Point bl, double b, double c) {
        v1 = b;
        v2 = c;
        return b;
    }

    public Point getTopRight() {
        return null;
    }

    public Point getBottomLeft() {
        return null;
    }

    public Point getBottomRight() {
        return null;
    }

    public Point getTopLeft() {
        return null;
    }

    public double getHeight() {
        return 0;
    }

    public double getWidth() {
        return 0;
    }

    @Override
    public double area() {
        return Math.PI * v1 * v2;
    }

    @Override
    public double perimeter() {
        return  2 * Math.PI * Math.sqrt((Math.pow(v1, 2) + Math.pow(v2, 2)) / 2);
    }

}
