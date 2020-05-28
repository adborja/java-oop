package com.edu.cedesistemas.oop.model.geometry;

<<<<<<< HEAD
public class Point extends Segment{
    private double x;
    private double y;

    public Point (double punto1,double punto2){
        x=punto1;
        y=punto2;
    }
    public double getX(){
        return getX();
    }
    public double getY(){
        return getY();
    }
    public double distancia(){
        return x+y;
    }

}
=======
import java.util.Objects;
import java.util.Random;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static Point of(double x, double y) {
        return new Point(x, y);
    }

    public static Point random(int bound) {
        double x1 = new Random().nextDouble() * bound;
        double y1 = new Random().nextDouble() * bound;
        return of(x1, y1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
>>>>>>> 0c7e45e58b1bb8fda2db72ba70825afe69151c48
