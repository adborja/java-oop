package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse {

    public Circle(double radio) {
        super(radio, radio);
    }

    public double perimeter() {
        return 2 * Math.PI * getA();
    }

    public double getRadius() {
        return getA();
    }
}