package com.edu.cedesistemas.oop.model.geometry;

public interface ComparableShape extends Shape {
    default boolean isBiggerThan(Shape shape) {
        return this.perimeter() > shape.perimeter();
    }
}
