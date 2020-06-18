package com.edu.cedesistemas.oop.functional;

import com.edu.cedesistemas.oop.model.geometryOK.Scalable;

public interface ShapeMultiplier<T extends Scalable<T, U>, U extends Number> {
    T multiply(T scalable, U value);
}
