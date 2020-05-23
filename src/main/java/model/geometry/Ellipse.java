package model.geometry;

public class Ellipse implements Shape {
    private double a;
    private double b;

    public Ellipse (double v1, double b) {
       a = v1 ;
       this.b = b  ;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return this.b;
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }

}
