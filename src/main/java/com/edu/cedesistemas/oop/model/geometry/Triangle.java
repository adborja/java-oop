package com.edu.cedesistemas.oop.model.geometry;

import javax.swing.text.Segment;

public class Triangle implements Shape{

    private Segment[] sides;
    private String type;

    public Triangle(Point p1, Point p2, Point p3){
    }

    public Triangle (Segment...sides){
        this.sides = sides;
    }

    public Segment[] getSides(){
        return sides;
    }

    public String getType(){
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

    public double angle(Segment x){
        return 0;
    }

    private void determineType(){

    }

/*    private removeSide(Segment[] segments, Segment segment){
        return ;
    }*/

    private boolean isIsosceles(Triangle t1){
        return true;
    }

    private boolean isEquilateral(Triangle t2){
        return true;
    }

    private boolean isScalene(Triangle t3){
        return true;
    }

}
