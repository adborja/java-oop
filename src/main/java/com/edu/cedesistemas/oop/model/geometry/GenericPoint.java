package com.edu.cedesistemas.oop.model.geometry;

import javax.print.attribute.standard.NumberUp;
import java.util.Objects;
import java.util.Random;

public class GenericPoint <T extends Number> {
    private T x;
    private T y;

    public GenericPoint(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public static <T extends Number> double distance(GenericPoint<T> object1, GenericPoint<T> object2) {
        return Math.sqrt(Math.pow(object1.x.doubleValue() - object2.x.doubleValue(), 2) + Math.pow(object1.y.doubleValue() - object2.y.doubleValue(), 2));
    }


    public static <T extends Number> GenericPoint<T> of(T x, T y) {
        GenericPoint<T> point = new GenericPoint<>(x, y);
        return point;
    }

    /*
    public static <T extends Number> GenericPoint<T> random(int bound) {
        GenericPoint<T> point = new GenericPoint<T>();
        return of(x1, y1);
    }
     */
}