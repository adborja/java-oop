package com.edu.cedesistemas.oop.generics;

public class Box<U> {
    private U u;

    public void set(U t) { this.u = t; }

    public U get() { return u; }
}
