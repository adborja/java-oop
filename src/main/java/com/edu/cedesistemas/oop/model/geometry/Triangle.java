package com.edu.cedesistemas.oop.model.geometry;

public class Triangle implements Shape{
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
    private void determineTipo(){
    }
    private void removeSide(Segment[] arreglo, Segment b){

    }
    private boolean isisoceles(Triangle iso){
        return true;
    }
    private boolean isescaleno(Triangle esca){
        return true;
    }
    private boolean isequilatero(Triangle equi){
        return true;
    }
}

