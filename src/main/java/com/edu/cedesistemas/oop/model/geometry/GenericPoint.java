package com.edu.cedesistemas.oop.model.geometry;

public class GenericPoint <T extends Number> {

    private T x;
    private T y;

    public GenericPoint(T x, T y){
        this.x = x;
        this.y = y;
    }

    public T getX(){
        return x;
    }

    public T getY(){
        return y;
    }

    public <N extends Number> double distance(GenericPoint<N> p1, GenericPoint<T> p2){
        return Math.sqrt(Math.pow(p1.x.intValue() - p2.x.intValue(),2)
        + Math.pow(p1.y.intValue() - p2.y.intValue(),2));
        //return 0;
    }

    public static <T extends Number> GenericPoint<T> of(T x, T y){
        return new GenericPoint<>(x,y);
    }

    public Point random(int n){
        return new Point(n,n);
    }
}
