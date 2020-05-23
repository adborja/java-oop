package com.edu.cedesistemas.oop;

import com.edu.cedesistemas.oop.model.geometry.Ellipse;
import com.edu.cedesistemas.oop.model.geometry.Rectangle;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello Java OOP!");
        Ellipse rectangle = new Ellipse(1, 2);
        System.out.println("A: " + rectangle.getA());
        System.out.println("B: " + rectangle.getB());
        System.out.println("Area: " + rectangle.area());
        System.out.println("Perimetro: " + rectangle.perimeter());
    }
}