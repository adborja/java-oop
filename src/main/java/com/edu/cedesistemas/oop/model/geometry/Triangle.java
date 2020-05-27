package com.edu.cedesistemas.oop.model.geometry;

import javax.swing.text.Segment;
import java.awt.*;

public class Triangle implements Shape {
    private Segment [] sides;
    private String type;

    public Triangle(Point p1, Point p2, Point p3){

    }
    public Triangle(Segment... Segment){

    }

    public Segment[] getSides() {
        return sides;
    }

    public String getType() {
        return type;
    }
    public double angle(Segment S1){
        return 1;
    }
    private void determineType(){

    }
    private void removeSide(Segment[] sides,Segment s1){

    }
    private boolean isIsosceles(Triangle T1){
        return true;
    }
    private boolean isEquilateral(Triangle T1){
        return false;
    }
    private boolean isScalene(Triangle T1){
        return true;
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
