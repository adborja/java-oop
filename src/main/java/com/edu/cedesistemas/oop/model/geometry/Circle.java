package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse{
    public Circle(double x){
        super(x, x);
    }

    public double getRadius(){
        return this.getA();
    }

    public double perimeter() {
        return 0;
    }
}
