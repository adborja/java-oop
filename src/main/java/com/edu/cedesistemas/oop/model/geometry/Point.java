package com.edu.cedesistemas.oop.model.geometry;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double distance(Point p1, Point p2){
        return 0;
    }

    public Point of(double x, double y){
        return new Point(x,y);
    }

    public Point random(int n){
        return new Point(n,n);
    }
}

