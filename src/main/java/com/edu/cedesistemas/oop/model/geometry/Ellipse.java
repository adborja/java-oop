package com.edu.cedesistemas.oop.model.geometry;

public class Ellipse implements Shape{
    private double a;
    private double b;

    public Ellipse(double a, double b){ }

    public double getA(){
        return this.a;
    }

    public double getB(){
        return this.b;
    }

    public double area(){
        return Math.PI * a * b;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2);
    }
}
