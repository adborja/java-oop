package com.edu.cedesistemas.oop.model.geometry;

public class Triangle implements Shape{
    private Segment sides;
    private String type;

    public Triangle(Point object1,Point object2,Point object3) {

    }

    public Triangle(Segment object1) {
    this.sides = object1;
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

    public double angle(Segment object1) {
        return 1;
    }

    private void determineType() {
        //pendiente
    }

    private void removeSide(Segment object1,Segment object2){
        //pendiente
    }

    private double isIsosceles(Triangle object1){
        return 1;
    }

    private double isEquilateral(Triangle object1){
        return 1;
    }

    private double isScaleno(Triangle object1){
        return 1;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
