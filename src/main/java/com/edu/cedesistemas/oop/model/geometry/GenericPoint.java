package com.edu.cedesistemas.oop.model.geometry;

public class GenericPoint<T extends Number> {

    private T x;
    private T y;

    public GenericPoint (T a, T b){
        x=a;
        y=b;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public static <T extends Number> GenericPoint<T>  of(T x, T y) {
          return new GenericPoint<T>(x, y);
    }

    public static <U extends Number> double distance(GenericPoint<U>  p1, GenericPoint<U> p2) {
        return Math.sqrt(Math.pow(p1.x.doubleValue() - p2.x.doubleValue(), 2) + Math.pow(p1.y.doubleValue() - p2.y.doubleValue(), 2));
    }
}
