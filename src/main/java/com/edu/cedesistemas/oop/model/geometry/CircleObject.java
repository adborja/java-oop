package com.edu.cedesistemas.oop.model.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleObject {
    public static void main(String[] args){
        List<Circle> circles = new ArrayList<>();
        double random;

        do {
            random = new Random().nextDouble();
            circles.add(new Circle(random));
        }while (random > 0.01);

        for (Circle circle: circles) {
            System.out.println("Area del Circulo: " +circle.area());
        }
    }
}
