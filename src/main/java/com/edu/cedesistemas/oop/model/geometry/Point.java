package com.edu.cedesistemas.oop.model.geometry;
public class Point {
    private double x;
    private double y;

    public Point(double x,double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double distance(Point object1,Point object2) {

        return x;
    }
    public Point of(double x,double y) {
        Point point = new Point(x,y);
        return point;
    }
    public Point random(int x) {
        Point point = new Point(x,x);
        return point;
    }
}