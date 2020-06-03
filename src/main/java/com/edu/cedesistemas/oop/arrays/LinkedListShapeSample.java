package com.edu.cedesistemas.oop.arrays;

import com.edu.cedesistemas.oop.model.geometry.Circle;
import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Rectangle;
import com.edu.cedesistemas.oop.model.geometry.Segment;
import com.edu.cedesistemas.oop.model.geometry.Shape;
import com.edu.cedesistemas.oop.model.geometry.Square;

import java.util.LinkedList;
import java.util.List;

public class LinkedListShapeSample {
    public static void main(String[] args) {
        List<Shape> shapes = new LinkedList<>();

        Shape rectangle = new Rectangle(Point.of(5, 4), 20, 40);
        Shape square = new Square(Point.of(5, 20), 15);
        Shape segment = new Segment(Point.of(-5, 10), Point.of(10, 15));
        Shape circle = new Circle(30);

        shapes.add(rectangle);
        shapes.add(square);
        shapes.add(segment);
        shapes.add(circle);

        for (Shape s : shapes) {
            System.out.println("Area of " + s + ": " + s.area());
        }
    }
}
