package com.edu.cedesistemas.oop.model.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle implements Shape {
    protected final Segment[] sides;

    public Triangle(Point p1, Point p2, Point p3) {
        Segment s1 = new Segment(p1, p2);
        Segment s2 = new Segment(p2, p3);
        Segment s3 = new Segment(p1, p3);
        this.sides = new Segment[] {s1, s2, s3};
    }

    public Triangle(Segment... sides) {
        this.sides = sides;
    }

    public Segment[] getSides() {
        return sides;
    }

    @Override
    public double area() {
        double _s1 = sides[0].getValue();
        double _s2 = sides[1].getValue();
        double _s3 = sides[2].getValue();

        double s = (_s1 + _s2 + _s3) / 2;
        return Math.sqrt(s * (s - _s1) * (s - _s2) * (s - _s3));
    }

    @Override
    public double perimeter() {
        return sides[0].getValue() + sides[1].getValue() + sides[2].getValue();
    }

    public double angle(Segment c) {
        Segment[] asides = removeSide(this.sides, c);
        if (asides != null) {
            Segment a = asides[0];
            Segment b = asides[1];
            double sum = Math.pow(a.getValue(), 2) + Math.pow(b.getValue(), 2) - Math.pow(c.getValue(), 2);
            double calc = sum / (2 * a.getValue() * b.getValue());
            return Math.toDegrees(Math.acos(calc));
        }
        return 0;
    }

    private static Segment[] removeSide(Segment[] segments, Segment segment) {
        List<Segment> _sides = new ArrayList<>(Arrays.asList(segments));
        if (_sides.remove(segment)) {
            Segment[] result = new Segment[_sides.size()];
            return _sides.toArray(result);
        }
        return null;
    }
}
