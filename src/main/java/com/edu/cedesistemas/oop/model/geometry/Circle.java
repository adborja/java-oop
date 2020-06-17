package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse implements Scalable<Circle, Double>, ComparableShape {
    public Circle(double radius) {
        super(radius, radius);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * getA();
    }

    public double getRadius() {
        return getRadius();
    }

    @Override
    public Circle scale(Double percentage) {
        return new Circle(getRadius() * percentage / 100);
    }

    @Override
    public String toString() {
        return "circle {radius: " + getRadius() + "}";
    }
}
