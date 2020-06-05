package com.edu.cedesistemas.oop.model.geometry;

public class ShapeScaler {
    public static <T extends Scalable<T>> T scale(T t, double percentage) {
        return t.scale(percentage);
    }
}