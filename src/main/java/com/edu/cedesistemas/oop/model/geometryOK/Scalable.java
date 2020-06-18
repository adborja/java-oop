package com.edu.cedesistemas.oop.model.geometryOK;

public interface Scalable<T, U extends Number> {
    T scale(U percentage);
}
