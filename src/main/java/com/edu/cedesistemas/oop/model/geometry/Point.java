package com.edu.cedesistemas.oop.model.geometry;

import java.util.Objects;
import java.util.Random;

public class Point <T extends Number> {
    private T x;
    private T y;

    public Point(T x, T y) {
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
        return Math.sqrt(Math.pow
                (p1.x.doubleValue() - p2.x.doubleValue(), 2)
                + Math.pow(p1.y.doubleValue() - p2.y.doubleValue(), 2));
    }

    public static <T extends Number> Point of(T x, T y) {
        return new Point(x, y);
    }

    public static Point random(int bound) {
        double x1 = new Random().nextDouble() * bound;
        double y1 = new Random().nextDouble() * bound;
        return of(x1, y1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x.doubleValue(), x.doubleValue()) == 0 &&
                Double.compare(point.y.doubleValue(), y.doubleValue()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}