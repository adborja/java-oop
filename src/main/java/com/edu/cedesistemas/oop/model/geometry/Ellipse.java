package com.edu.cedesistemas.oop.model.geometry;

public class Ellipse implements Shape {
    protected final double a;
    private final double b;

    public Ellipse(double a, double b) {
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

        //double area = Math.PI * a * b;
        return Math.PI * a * b;
    }

    @Override
    public double perimeter() {
        /*double p = 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2);
        return p;*/
        return 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2);
    }
}

/*public class Ellipse implements Shape{
    private double a;
    private double b;

    public Ellipse (double z , double b) {
        a=z;
        this.b=b;
    }

    public double getA(){
        return a;
    }
    public double getB(){
        return this.b;
*/