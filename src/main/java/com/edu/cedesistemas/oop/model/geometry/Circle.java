package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse implements Scalable, ComparableShape {
    public Circle(double radius) {
        super(radius, radius);
    }

    @Override
    public double perimeter() {
        //return 2 * Math.PI * a;
        return 2 * Math.PI * getA();//pendiente resolver
    }

    public double getRadius() {
        //return a;
        return getA();//pendiente resolver
    }

    @Override
    public Shape scale(double percentage) {
        return new Circle(getRadius() * percentage / 100);
    }
}