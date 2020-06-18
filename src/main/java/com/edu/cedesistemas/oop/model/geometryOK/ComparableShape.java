package com.edu.cedesistemas.oop.model.geometryOK;

public interface ComparableShape extends Shape {
    default boolean isBiggerThan(Shape shape) {
        return this.perimeter() > shape.perimeter();
    }
}
