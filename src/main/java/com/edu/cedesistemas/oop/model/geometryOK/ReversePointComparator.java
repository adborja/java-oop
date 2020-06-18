package com.edu.cedesistemas.oop.model.geometryOK;

import java.util.Comparator;

public class ReversePointComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            return 0;
        }
        return p1.getX() < p2.getX() && p1.getY() < p2.getY() ? 1 : -1;
    }
}
