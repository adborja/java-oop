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


    public static Point of(double x, double y) {
        return new Point(x, y);
    }

    public static Point random(int i){
        return new Point(i,i);
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

}
