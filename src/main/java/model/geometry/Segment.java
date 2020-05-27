package model.geometry;

import javafx.scene.effect.Light;

import java.awt.*;

public class Segment implements Shape {
    private Point p1;
    private Point p2;
    private String name;

    public Point Segment(Point a, Point b){
        return a;
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
