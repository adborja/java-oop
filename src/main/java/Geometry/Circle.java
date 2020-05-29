package Geometry;

public class Circle extends Ellipse{

    public Circle(double radio) {

        super(radio, radio);
    }

    public double getRadius(){

        return getA();
    }

    @Override
    public double perimeter() {

        double peri= 2 * Math.PI * getA();
        return peri;
    }
}


