package com.edu.cedesistemas.oop.model.geometry;

import java.util.Objects;
import java.util.Random;

public class GenericPoint<T extends Number> {
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

    public static <K extends Number> GenericPoint<K> of(K x, K y) {
        return new GenericPoint<K>(x, y);
    }

    public static <Y extends Number> GenericPoint<Y> random(int bound) {
        double x1 = new Random().nextDouble() * bound;
        double y1 = new Random().nextDouble() * bound;
        return of(x1, y1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericPoint<Z> point = (GenericPoint) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
