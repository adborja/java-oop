package com.edu.cedesistemas.oop.model.geometry;

public class Point {
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double distance(Point pt1, Point pt2) {
        return 0;
    }

    public static Point of(double x, double y) {
        Point pt = new Point(x, y);
        return pt;
    }

    public static Point random(int nro) {
        Point pt = new Point(nro, nro);
        return pt;
    }
}
