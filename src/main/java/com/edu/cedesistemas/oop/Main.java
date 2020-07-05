package com.edu.cedesistemas.oop;

import com.edu.cedesistemas.oop.functional.SquareRoot;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Java OOP!");

        SquareRoot sr = n -> Math.sqrt(n);
        double result = sr.findSquareRoot(100);
        System.out.println("Result: " + result);
    }
}