package Geometry;

public class Ellipse implements Shape{
    private double a;
    private double b;

    public Ellipse(double z, double b)  {
        a = z;
        this.b = b;
    }


    public double getA(){
        return a;
    }

    public double getB(){
        return b;
    }

    @Override
    public double area() {
        double area = Math.PI*a*b;
        return area;
    }

    @Override
    public double perimeter() {
        double per = Math.sqrt((Math.pow(a,2) + Math.pow(b,2))/2);
        return per;
    }
}
