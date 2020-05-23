package com.edu.cedesistemas.oop.model.geometry;

import javax.swing.text.Segment;
import java.awt.*;

public class Triangle implements Shape {
    private Segment[] sides;
    private String type;
    public Triangle(Point ld1, Point ld2, Point ld3) {

    }
    public Triangle(Segment[] sides) {

    }

    public Segment[] getSides() {
        return sides;
    }

    public String getType() {
        return type;
    }

    public double angle(Segment segment) {
        return 0;
    }

    private void determineType() {

    }

    private void removeSide(Segment[] sides, Segment segment) {

    }

    private boolean isIsosceles(Triangle triangle) {
        return true;
    }

    private boolean isEquilateral(Triangle triangle) {
        return true;
    }

    private boolean isScalene(Triangle triangle) {
        return true;
    }

    @Override
    public double area() {
        //return Math.PI * a * b;}
        return 0;
    }

    @Override
    public double perimeter() {
        //return (2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2));}
        return 0;
    }
}
