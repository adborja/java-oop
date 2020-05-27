package com.edu.cedesistemas.oop.model.geometry;

import javax.swing.text.Segment;

public class Triangle implements Shape {
    private Segment sides;
    private String type;

    public Triangle(Point a, Point b, Point c){
    }

    public Triangle(Segment a){
        this.sides = a;
    }

    public Segment getSides() {
        return sides;
    }

    public String getType() {
        return type;
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }

    public double angle(Segment a) {
        return 0;
    }

    private void determineType() {
    }

    private Segment removeSide(Segment a, Segment b){
        return null;
    }

    private boolean isIsosceles(Triangle a){
        return true;
    }

    private boolean isEquilateral(Triangle a){
        return true;
    }

    private boolean isScalene(Triangle a){
        return true;
    }

}
