package com.edu.cedesistemas.oop.model.geometry;

import java.util.Comparator;

public class CircleRadiusComparator implements Comparator<Circle> {

    @Override
    public int compare(Circle circle1, Circle circle2) {
        if (circle1.getRadius() == circle2.getRadius()) {
            return 0;
        }
        return circle1.getRadius() > circle2.getRadius() ? 1 : -1;
    }
}
