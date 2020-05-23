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
        return Math.PI * a * this.b;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(this.b, 2)) / 2);
    }

}
