package com.edu.cedesistemas.oop.model.geometry;

public class Rectangle implements Shape{
    private Point bl;
    private Point tr;
    private Point br;
    private Point tl;
    private double height;
    private double width;

    public Rectangle(Point object1,double height,double width) {

        Point point = new Point(height,width);
        this.bl = object1;
        this.height = height;
        this.width = width;
    }

    public Point getTopRight() {
        return tr;
    }

    public Point getBottomleft() {
        return bl;
    }

    public Point getBottomRight() {
        return br;
    }

    public Point getTopLeft() {
        return tl;
    }

    public double getwidth() {
        return width;

    }

    public double getheight() {
        return height;
    }

    @Override
    public double area() {
        //return (Math.PI * height * width);
        return height * width;
    }

    @Override
    public double perimeter() {
        return (2 * Math.PI * Math.sqrt((Math.pow(height, 2) + Math.pow(width, 2)) / 2));
    }


}
