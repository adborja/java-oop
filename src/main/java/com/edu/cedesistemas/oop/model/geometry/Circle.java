package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse {

    public Circle(double r) {
        super(r, r);
    }

    public double getRadius(){
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }
}
