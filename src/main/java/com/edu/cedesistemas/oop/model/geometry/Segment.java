package com.edu.cedesistemas.oop.model.geometry;

public class Segment<string> implements Shape {
    private point p1;
    private point p2;
    private string name;

    //constructor
    public Segment(point p1, point p2, string name) {
        this.p1 = p1;
        this.p2 = p2;
        this.name = name;
    }

    public point getP1() {
        return p1;
    }

    public point getP2() {
        return p2;
    }

    public string getName() {
        return name;
    }

    public double getValue() {
        double x = 0;
        return x;
    }

    public double slope() {
        double y = 0;
        return y;
    }

    @Override
    public double area() {
        double z = 0;
        return z;
    }

    @Override
    public double perimeter() {
        double p = 0;
        return p;
    }

    private class point {
    }
}
