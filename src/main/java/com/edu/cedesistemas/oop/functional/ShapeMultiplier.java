package com.edu.cedesistemas.oop.functional;

import com.edu.cedesistemas.oop.model.geometry.Scalable;

public interface ShapeMultiplier<T extends Scalable, U extends Number> {
    T multiply(T scalable, U value);
}
