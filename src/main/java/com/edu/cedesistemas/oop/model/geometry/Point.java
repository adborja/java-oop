package com.edu.cedesistemas.oop.model.geometry;

public class Point<T extends Number> {
    private T x;
    private T y;

    public Point(T x,T y) {
        this.x = x;
        this.y = y;
    }

    public <T extends Number> double getX() {
        return x.doubleValue();
    }

    public <T extends Number> double getY() {
        return y.doubleValue();
    }


    public static <T extends Number> double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x.doubleValue() - p2.x.doubleValue(), 2)
                + Math.pow(p1.y.doubleValue() - p2.y.doubleValue(), 2));
    }

    public static <T extends Number> Point of(T x, T y) {
        Point point = new Point(x,y);
        return point;
    }

    public static <T extends Number> Point random(T x) {
        Point point = new Point(x,x);
        return point;
    }
}
