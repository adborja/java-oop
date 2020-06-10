package com.edu.cedesistemas.oop.model.geometry;

<<<<<<< HEAD
public class GenericPoint<T extends Number>{
    private T x;
    private T y;

    public GenericPoint(T x, T y) {
=======
import java.util.Objects;
import java.util.Random;

public class GenericPoint<X extends Number, Y extends Number> {
    private final X x;
    private final Y y;

    public GenericPoint(X x, Y y) {
>>>>>>> 06995361a37e700caa48f10539ab9b5c8665b907
        this.x = x;
        this.y = y;
    }

<<<<<<< HEAD
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
=======
    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public static <T extends Number> double distance(GenericPoint<T, T> p1,
                                                     GenericPoint<T, T> p2) {
        return Math.sqrt(Math.pow(p1.getX().doubleValue() - p2.getX().doubleValue(), 2)
                + Math.pow(p1.getY().doubleValue() - p2.getY().doubleValue(), 2));
    }

    public static <X extends Number, Y extends Number> GenericPoint<X, Y> of(X x, Y y) {
        return new GenericPoint<>(x, y);
    }

    public static GenericPoint<Double, Double> random(int bound) {
        Double x1 = new Random(bound).nextDouble();
        Double y1 = new Random(bound).nextDouble();
        return of(x1, y1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericPoint<?, ?> that = (GenericPoint<?, ?>) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
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
>>>>>>> 06995361a37e700caa48f10539ab9b5c8665b907
