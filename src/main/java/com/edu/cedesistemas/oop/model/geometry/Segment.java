package com.edu.cedesistemas.oop.model.geometry;

import java.awt.*;

public class Segment implements Shape {
    private Point p1;
    private Point p2;
    private String name;

    public Segment(Point p1, Point p2) {
        this.p1 = (Point) p1;
        this.p2 = (Point) p2;
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

    public double getValue() {
        return 0;
    }

    public double slope() {
        return 0;
    }

    @Override
    public double area() {
        return Math.PI * p1.getX() * p2.getY();
    }

    @Override
    public double perimeter() {
        return (2 * Math.PI * Math.sqrt((Math.pow(p1.getX(), 2) + Math.pow(p2.getY(), 2)) / 2));
    }
}
