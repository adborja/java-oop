package com.edu.cedesistemas.oop.model.geometry;

public class Segment implements Shape{

    private Point p1;
    private Point p2;
    private String name;

    public Segment(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1(){
        return p1;
    }

    public Point getP2(){
        return p2;
    }

    public String getname(){
        return name;
    }
     public double Slope(){
        return 0;
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
