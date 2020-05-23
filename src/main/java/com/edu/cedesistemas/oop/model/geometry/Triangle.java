package com.edu.cedesistemas.oop.model.geometry;

import javax.swing.text.Segment;
import java.awt.*;

public class Triangle implements Shape{
    private Segment sides;
    private String type;

    public Triangle (Point pos1, Point pos2, Point pos3) {

    }

    public Triangle (Segment segment) {

    }

    public Segment getSides() {
        return sides;
    }

    public String getType() {
        return type;
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }

    public Double angle(Segment seg1) {
        return Double.valueOf(0);
    }

    void determineType() {

    }

    Segment removeSide(Segment seg1) { //Segun diseño tiene dos parametros segmento, pero que tipo es? o deberìa ser void?
        return seg1;
    }

    Boolean isIsosceles(Triangle triangle) { //Cual es el nombre de este concepto?
        return false;
    }

    Boolean isEquilateral(Triangle triangle) {
        return true;
    }

    Boolean isScalene(Triangle triangle) {
        return false;
    }
}
