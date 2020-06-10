package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse{

    public Circle(double radio) {
        super(radio,radio);
    }

    public double getRadius() {
       return getA();
    }

    @Override
    public double perimeter() {
        double p = 2 * Math.PI * getA();
        return p;
    }




}
