package com.edu.cedesistemas.oop.model.geometry;

public class Point {
    private double x;
    private double y;

    public Point (double a, double b){
        x=a;
        y=b;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point p1, Point p2){
        return 0;
    }

    public Point of(double d1, double d2){
        return new Point(d1,d2);
    }

    public Point random (int i){
        return new Point(i,i);
    }

}
