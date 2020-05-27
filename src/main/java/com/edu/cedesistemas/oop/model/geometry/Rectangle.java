package com.edu.cedesistemas.oop.model.geometry;

public class Rectangle implements Shape{

    private Point bl;
    private Point tr;
    private Point br;
    private Point tl;
    private double heigth;
    private double width;

    public Rectangle(Point bl, double heigth, double width) {
        this.bl = bl;
        this.heigth = heigth;
        this.width = width;
    }

    public Point getTopRigth(){
        return this.tr;
    }

    public Point getBottomLeft(){
        return this.bl;
    }

    public Point getBottomRigth(){
        return this.br;
    }

    public Point getTopLeft(){
        return this.tl;
    }

    public double getHeigth(){
        return heigth;
    }

    public double getWidth(){
        return width;
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
