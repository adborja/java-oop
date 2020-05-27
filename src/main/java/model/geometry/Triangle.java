package model.geometry;

import java.awt.*;

public class Triangle implements Shape {
    private String type;
    private Segment[] side;

    public Point Triangle(Point a, Point b, Point c) {
        return a;
    }

    public int Triangle(Segment... d) {
        return 0;
    }

    public Segment[] getSide() {
        return null;
    }

    public String getType() {
        return null;
    }

    public double angle(Segment[] a) {
        return 0;
    }

    public void determineType() {

    }

    public Segment[] removeSide(Segment[] a, Segment b) {
        return null;
    }

    public boolean isIsoceles(Point a, Point b, Point c) {
        return true;
    }

    public boolean isEquilateral(Segment... d) {
        return true;
    }

    public boolean isScalene(Segment... d) {
        return true;
    }

        @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }
}
