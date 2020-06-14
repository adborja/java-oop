package com.edu.cedesistemas.oop.model.geometry;

public interface Scalable<T, U extends Number> {
    T scale(U percentage);
}