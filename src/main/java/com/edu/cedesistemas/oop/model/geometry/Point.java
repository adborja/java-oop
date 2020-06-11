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


    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static Point of(double x, double y) {
        Point point = new Point(x,y);
        return point;
    }

    public static Point random(int x) {
        Point point = new Point(x,x);
        return point;
    }
}
