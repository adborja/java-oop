package com.edu.cedesistemas.oop.model.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShapeSample {
    public static void main(String[] args) {
        Circle circle1 = new Circle(10);
        Circle circle2 = new Circle(5);
        Circle circle3 = new Circle(7);
        Circle circle4 = new Circle(12);

        List<Circle> circles = new ArrayList<>();
        circles.add(circle1);
        circles.add(circle2);
        circles.add(circle3);
        circles.add(circle4);

        CircleRadiusComparator radiusComparator = new CircleRadiusComparator();
        System.out.println("before sorting: " + circles);
        //Collections.sort(circles, radiusComparator);
        Collections.sort(circles);
        System.out.println("after sorting: " + circles);

        // Scaler sample (generics)...
/*
        Circle scaledCircle = ShapeScaler.scale(circle1, 50);
        System.out.println("scaled circle: " + scaledCircle);*/

    }
}