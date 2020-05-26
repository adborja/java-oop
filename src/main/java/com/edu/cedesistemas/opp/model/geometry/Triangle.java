package com.edu.cedesistemas.opp.model.geometry;

<<<<<<< HEAD
import java.awt.*;

public class Triangle implements Shape {

    private Segment sides;
    private String type;

    public Triangle (Point a, Point b, Point c){
        //this.a =a;
        //this.b =b;
        //this.c =c;

    }

    public  Triangle (Segment obj){
        this.sides = obj;
    }

    public Segment getSides() {
        return sides;
    }

    public String getType() {
        return type;
    }


    @Override
    public double area() {
        return 1;
    }

    @Override
    public double perimeter() {
        return 1;
    }

    public double angle(Segment obj){
        return 1;
    }

    private void removeSide(Segment obj, Segment obj1){
        // return 1; No lo tengo Claro
    }

    private void determinateType(){
        // return 1; No lo tengo Claro/
    }

    private  boolean isIsosceles(Triangle obj){
        return false;// return isIsosceles(); No lo tengo Claro
    }

    private boolean isEquilateral(Triangle obj){
        return false; // No lo tengo Claro
    }

    private boolean isScalene(Triangle obj){
        return false; // return isScalene(); No lo tengo Claro
    }

}

=======
public class Triangle {
}
>>>>>>> origin/feature/dhoyos-javaopp
