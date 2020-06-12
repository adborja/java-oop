package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse implements Scalable<Circle> {
    public Circle(double radius) {
        super(radius, radius);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * 1;
    }

    public double getRadius() {
        return 1;
    }

    @Override
    public Circle scale(double percentage) {
        return new Circle(getRadius() + getRadius() * percentage / 100);
    }

    @Override
    public String toString() {
        return "circle {radius: " + getRadius() + "}";
    }

    @Override
    public Circle scale(Number percentage) {
        return null;
    }
}
