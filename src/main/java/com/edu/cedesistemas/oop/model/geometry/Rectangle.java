package com.edu.cedesistemas.oop.model.geometry;

// Lesson 1 -- Classes
public class Rectangle implements Shape {
    private final Point bl;
    private final Point tr;
    private final Point br;
    private final Point tl;
    private final double height;
    private final double width;

    // Lesson 1 -- Constructores
    public Rectangle(Point bl, double w, double h) {
        this.bl = bl;
        this.tl = Point.of(bl.getX(), bl.getY() + h);
        this.br = Point.of(bl.getX() + w, bl.getY());
        this.tr = Point.of(bl.getX() + w, bl.getY() + h);
        this.height = h;
        this.width = w;
    }

    public Point getTopRight() {
        return tr;
    }

    public Point getBottomLeft() {
        return bl;
    }

    public Point getBottomRight() {
        return br;
    }

    public Point getTopLeft() {
        return tl;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double area() {
        return getHeight() * getWidth();
    }

    @Override
    public double perimeter() {
        return 2 * (getHeight() + getWidth());
    }
}
