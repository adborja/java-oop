package com.edu.cedesistemas.oop.model.geometry;

public class Point extends Segment{
    private double x;
    private double y;

    public Point(double x, double y){
        super(new Point(x, y), new Point(x, y));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point p1, Point p2){
        return  0;
    }

    public Point od(double p1, double p2){
        return  new Point(0, 0);
    }

    public Point random(int n){
        return  new Point(0, 0);
    }
}
