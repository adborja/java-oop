package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.geometry.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//Taller Lección 4 – Collections, Ejercicio 1
public class CircleList {

    public static void main(String[] args) {
        List<Circle> listCircles = new ArrayList<>();
        double radio = new Random().nextDouble();

        while (radio > 0.01) {
            listCircles.add(new Circle(radio));
            radio = new Random().nextDouble();
        }

        for (Circle circle : listCircles) {
            System.out.println(circle.area());
        }
    }

}
