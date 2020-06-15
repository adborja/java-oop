package com.edu.cedesistemas.oop.functional;

import com.edu.cedesistemas.oop.model.geometry.Scalable;

public class ShapeMultiplierImpl<T extends Scalable, U extends Number> implements ShapeMultiplier<T, U> {
    @Override
    public T multiply(T scalable, U value) {
        return (T) scalable.scale((Double) value);//pendiente resolver
    }
}
