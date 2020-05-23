package com.edu.cedesistemas.opp.model.geometry;

public class Ellipse implements Shape {
    private double a;
    private double b;

    public Ellipse (double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    public double area() {
        //double area = Math.PI * a * b; - Tambien se puede hacer de esta forma
        return area = Math.PI * a * b;
    }

    @Override
    public double perimeter() {
        //double p = 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2) - Tambien se puede hacer de esta forma
        return p = 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2);
    }
}
