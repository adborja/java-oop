package com.edu.cedesistemas.oop.model.geometry;

<<<<<<< HEAD
public class Circle extends Ellipse {

    public Circle(double r){
        super(r,r);
    }
    public double perimetro(){
        return 2 *  Math.PI*getA();
    }
    public double getRadio(){
        return getA();
    }

=======
public class Circle extends Ellipse implements Scalable, ComparableShape {
    public Circle(double radius) {
        super(radius, radius);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * a;
    }

    public double getRadius() {
        return a;
    }

    @Override
    public Shape scale(double percentage) {
        return new Circle(getRadius() * percentage / 100);
    }
>>>>>>> 0c7e45e58b1bb8fda2db72ba70825afe69151c48
}
