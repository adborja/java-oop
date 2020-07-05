package com.edu.cedesistemas.oop.tareascollections;

import com.edu.cedesistemas.oop.model.geometry.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListCircles {

    public void listCircles (){
        List<Circle> listcircle = new ArrayList<>();
        double a;

        do {
          a = new Random().nextDouble();
          listcircle.add(new Circle(a));
            System.out.println(a);
        } while (a >= 0.01);

        System.out.println(listcircle.size());
        for(Circle circle : listcircle){
            System.out.println(circle.area());
        }
    }
}
