package com.edu.cedesistemas.oop.model.geometry;

public class Ellipse implements Shape {
    private double a;
    private double b;

    public Ellipse(double a, double b) {
        this.a = a;
        this.b = b;
    }
    public double getA() {
        return this.a;
    }

    public double getB() {
        return this.b;
    }

    @Override
    public double area() {
        double ar = Math.PI * a * b;
        return ar;
    }

    @Override
    public double perimeter() {
        double p = 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2);
        return p;
    }


}
