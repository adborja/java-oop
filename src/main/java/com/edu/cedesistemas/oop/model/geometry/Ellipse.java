package com.edu.cedesistemas.oop.model.geometry;

public class Ellipse implements Shape {
    //Creo los atributos y variables
    private double a;
    private double b;

    // Creo el constructor

    public Ellipse (double a, double b) {
        this.a = a;
        this.b = b;

    }

    //Creo los metodos

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    public double area() {
        return Math.PI * a * b;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2);
    }

}
