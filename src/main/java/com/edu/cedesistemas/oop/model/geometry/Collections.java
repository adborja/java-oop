package com.edu.cedesistemas.oop.model.geometry;

import com.edu.cedesistemas.oop.model.vehicles.ElectricCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Collections {
    public Collections(){

    }

    public void listCircles(){
        List<Circle> circles = new ArrayList<>();
        double random=10;
        while (random > 0.001){
            random = new Random().nextDouble();
            circles.add(new Circle(random));
        }

        for(Circle circle: circles){
            System.out.println("Area del Circulo: " + circle.area());
        }
    }
}
