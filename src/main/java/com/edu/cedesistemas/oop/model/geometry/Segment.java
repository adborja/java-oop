package com.edu.cedesistemas.oop.model.geometry;

import java.awt.*;

public class Segment implements Shape{

    private Point p1;
    private Point p2;

    Segment(Point p1, Point p2){

    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public String getName(){
        return "";
    }

    public double getValue(){
        return 0;
    }

    public double area(){
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }

}
