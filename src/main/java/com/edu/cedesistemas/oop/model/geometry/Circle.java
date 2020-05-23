package com.edu.cedesistemas.oop.model.geometry;

public class Circle extends Ellipse{

    public Circle(double radio) {
       //Invoca al constructor del padre Ellipse con dos valores por eso se repite radio
        // Argumento es radio y el parametro es a de la clase Ellipse
       super(radio,radio);
    }
    public double getRadius() {
        return getA();
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * getA();
    }


}
