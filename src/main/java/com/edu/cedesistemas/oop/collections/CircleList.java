package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.geometry.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleList {

    public static void main(String args[]){

        List<Circle> list = new ArrayList<>();
        double random;
        do {
            random = new Random().nextDouble();
            list.add(new Circle(random));
            } while (random > 0.01);

        for(Circle circle : list){
            System.out.println(circle.area());
        }
    }

}
