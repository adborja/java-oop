package com.edu.cedesistemas.oop.model.geometry;

public class Ellipse implements Shape {
    private double a;
    private double b;

    public Ellipse(double z, double b) {
        a = z;
        this.b = b;
    }
    public double getA() {
        return a;
    }
    public double getB() {
        return this.b;
    }

    @Override
    public double area() {
        return Math.PI * a * b;
    }
    @Override
    public double perimeter() {
        return  2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2);
    }
}
