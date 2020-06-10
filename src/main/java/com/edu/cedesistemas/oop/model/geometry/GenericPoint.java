package com.edu.cedesistemas.oop.model.geometry;

public class GenericPoint<T extends Number>{
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

    public static <R extends Number> double distance(GenericPoint<R> p1, GenericPoint<R> p2) {
        return Math.sqrt(Math.pow(p1.x.doubleValue() - p2.x.doubleValue(), 2)
                + Math.pow(p1.y.doubleValue() - p2.y.doubleValue(), 2));
    }

    public static <Y extends Number> GenericPoint<Y> of(Y x, Y y) {
        GenericPoint<Y> point = new GenericPoint<Y>(x,y);
        return point;
    }

}