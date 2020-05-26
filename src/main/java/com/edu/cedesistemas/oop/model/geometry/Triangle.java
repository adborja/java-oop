package com.edu.cedesistemas.oop.model.geometry;

public class Triangle implements Shape{

    private Segment sides;
    private String type;

    public Triangle (Point x, Point y, Point z){

    }

    public Segment getSides() {
        return sides;
    }

    public String getType() {
        return type;
    }


    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }
}
