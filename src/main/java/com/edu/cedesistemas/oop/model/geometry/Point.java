package com.edu.cedesistemas.oop.model.geometry;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
}

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distance(Point a, Point b) {
        return 0;
    }
    public Point of(double c, double d){
        Point g =  new Point(c, d);
        return g;
    }
    public Point random(int e){
        Point h =  new Point(e, e);
        return h;
    }
}