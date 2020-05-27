package com.edu.cedesistemas.oop.model.geometry;

import java.awt.*;

public class Triangle implements Shape{
    private Segment[] sides;
    private String type;

    public Triangle(Point a, Point b, Point c){

    }

    public Triangle(Segment sides){

    }

    public Segment[] getSides() {
        return sides;
    }

    public String getType() {
        return type;
    }

    public double area(){
        return 0;
    }

    public double perimeter() {
        return 0;
    }

    private void determineType(){

    }

    private void removeSide(Segment[] segments, Segment segment){

    }

    private boolean isIsosceles(Triangle triangle){
        return true;
    }

    private boolean isEquilateral(Triangle triangle){
        return true;
    }

    private boolean isScalene(Triangle triangle){
        return true;
    }

}
