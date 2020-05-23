package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse{


    Ellipse ellipse;

    public Circle(double radio) {
        ellipse.Ellipse(radio, radio);
    }

    public double getRadious(double a) {
        return  (2 * Math.PI * a);
    }

    @Override
    public double perimeter() {
        return super.perimeter();
    }
}
