package com.edu.cedesistemas.oop.model.geometryOK;

public interface Shape extends Comparable<Shape> {
    double area();
    double perimeter();

    @Override
    default int compareTo(Shape shape) {
        if (this.perimeter() == shape.perimeter()) {
            return 0;
        }
        return this.perimeter() > shape.perimeter() ? 1 : -1;
    }
}
