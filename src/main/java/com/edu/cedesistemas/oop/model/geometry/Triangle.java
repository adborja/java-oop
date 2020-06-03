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
        double p = Math.PI*4;
        return p;
    }
    public double angulo(Segment distancia){
        double agl;
        agl = Math.PI/distancia.getValue();
        return agl;
    }
    /*public double angle(Segment c) {
        Segment[] asides = removeSide(this.sides, c);
        if (asides != null) {
            Segment a = asides[0];
            Segment b = asides[1];
            double sum = Math.pow(a.getValue(), 2) + Math.pow(b.getValue(), 2) - Math.pow(c.getValue(), 2);
            double calc = sum / (2 * a.getValue() * b.getValue());
            return Math.toDegrees(Math.acos(calc));
        }
        return 0;
    }*/
    private void determineTipo(){
    }
    /*private void removeSide(Segment[] arreglo, Segment b){

    }*/
    private boolean isisoceles(Triangle iso){
        return true;
    }
    private boolean isescaleno(Triangle esca){
        return true;
    }
    private boolean isequilatero(Triangle equi){
        return true;
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


/*public class Triangle implements Shape{
    private Segment[] lados;
    private String tipo;


    public Triangle(Segment[] l){
        l=lados;
    }
    public Triangle(Point l1, Point l2, Point l3){

    }
    public Segment[] getLados(){
        return getLados();
    }
    public String getTipo(){
        return getTipo();
    }

    @Override
    public double area() {
        double a= Math.PI*3;
        return a;
=======*/