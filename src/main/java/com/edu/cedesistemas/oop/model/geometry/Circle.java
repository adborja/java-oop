package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse {

    public Circle(double r){
        super(r,r);
    }
    public double perimetro(){
        return 2 *  Math.PI*getA();
    }
    public double getRadio(){
        return getA();
    }

}
