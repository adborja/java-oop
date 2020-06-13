package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse implements Scalable<Circle,Number>, ComparableShape {
    private double radius;

    public Circle(double radius) {
        super(radius, radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Circle scale(Number percentage) {
        return new Circle(getRadius() + getRadius() * percentage.doubleValue() / 100);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "circle {radius: " + getRadius() + "}";
    }

}
