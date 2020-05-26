package com.edu.cedesistemas.oop.model.geometry;

public class Segment implements Shape{

    private Point p1;
    private Point p2;
    private String name;

    public Segment(Point p1, Point p2, String name) {
        this.p1 = p1;
        this.p2 = p2;
        this.name = name;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public String getName() {
        return name;
    }

    public void getValue(double a){

    }

    public void getSlope(){

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
