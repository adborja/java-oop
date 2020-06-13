package com.edu.cedesistemas.oop.model.geometry;

public interface Scalable<T extends Scalable, U extends Number> extends Shape {
    Shape scale(double percentage);
}
