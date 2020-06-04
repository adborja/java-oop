package com.edu.cedesistemas.oop.model.geometry;

import java.util.Objects;

public class Segment implements Shape {
    private final Point p1;
    private final Point p2;
    private final String name;

    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.name = p1.toString() + " ---> " + p2.toString();
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public String getName() {
        return name;
    }

    public double length() {
        return Point.distance(p1, p2);
    }

    public double getValue() {
        return Math.abs(Point.distance(p1, p2));
    }

    public double slope() {
        return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Double.compare(segment.getValue(), getValue()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return getValue();
    }
}
