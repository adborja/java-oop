package com.edu.cedesistemas.opp.model.geometry;

public class Circle extends Ellipse{

    public Circle(double radio) {
        super(radio, radio);
    }

    public double perimeter() {
        //double p = 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2) - Tambien se puede hacer de esta forma
        return p = 2 * Math.PI * getA();
    }

    public double getRadius() {
        //double p = 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2) - Tambien se puede hacer de esta forma
        return getA();
    }

}

