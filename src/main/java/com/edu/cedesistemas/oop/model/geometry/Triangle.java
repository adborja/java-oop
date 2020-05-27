package com.edu.cedesistemas.oop.model.geometry;

public class Triangle implements Shape {

    private Point a;
    private Point b;
    private Point c;
    private Segment sides[];
    private String type;

    public Triangle(Point a, Point b, Point c ){
        this.a=a;
        this.b=b;
        this.c=c;
    }

    public Triangle (Segment... segments) {
        this.sides=segments;
    }

    public Segment[] getSides() {
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

    public double angle(Segment segment){
        return 0;
    }

    private void determineType(){
    }

    private void removeSides  (Segment[] segments, Segment s){
    }

    private boolean isIsosceles(Triangle t){
        return true;
    }

    private boolean isEquilateral(Triangle t){
        return  true;
    }

    private boolean isScalene(Triangle t){
        return  true;
    }
}
