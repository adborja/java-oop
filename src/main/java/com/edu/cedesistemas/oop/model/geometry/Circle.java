package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse {

// Me pide que cree constructor porque el padre tiene uno con argumentos
    public Circle(double radio) {
        //Tengo dos argumentos porque el padre Elipse tiene 2
        super(radio, radio);
    }

    //Creo los metodos propios del circulo

    public double getRadius() {
        return getA();
    }

    public double perimeter() {
        return 2 * Math.PI * getA();
    }

}
