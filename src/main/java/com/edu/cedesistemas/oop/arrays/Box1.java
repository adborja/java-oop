package com.edu.cedesistemas.oop.arrays;

public class Box1<T> {
    private T t;

    public Box1(T t) {
    }

    public void set(T t) {this.t = t;}

    public T get() { return t;}
}
