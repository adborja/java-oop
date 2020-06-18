package com.edu.cedesistemas.oop.model.geometry;

import java.awt.*;

public class Rectangle implements Shape{
    private Point bl;
    private Point tr;
    private Point br;
    private double height;
    private double width;

    public Rectangle(Point pos, double height, double width) { //Por qué no tiene todos los campos Point en el constructor?

    }

    public Rectangle(Point bl, double size) {
    }

    public Point getBl() {
        return bl;
    }

    public Point getTr() {
        return tr;
    }

    public Point getBr() {
        return br;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double area() {
        System.out.println("height: " + height);
        System.out.println("width: " + width);
        System.out.println("area: " + height * width);
        return height * width;
    }

    @Override
    public double perimeter() {
        return 0;
    }
}
