package com.edu.cedesistemas.oop.maps;

import com.edu.cedesistemas.oop.model.geometry.Circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spanglish {
    public static void main(String[] args) {

        Map<Integer, String[]> numbers = new HashMap<>();
        String[] one = new String[]{"One", "Uno"};
        String[] two = {"Two", "dos"};
        String[] three = {"three", "tres"};
//TO_DO: Mirar diapositivas como se resolvió este ejercicio.ITERABLE?
//        List<String> numDesc = new ArrayList<>();
//        for (int i=0; i<5; i++) {
//            numDesc.add(i,i==1?"One" : "Two");
//        }
        numbers.put(1, one);
        numbers.put(2, two);
        numbers.put(3, three);
        System.out.println(numbers.values());
        System.out.println(numbers.get(2));
        System.out.println(numbers.get(3));

//        Map<Integer, List<String>> numbersList = new HashMap<>();
//        List<String> lista1 = new ArrayList<>();
//        lista1.add(1,"One");
//        lista1.add(2,"Uno");
//
//        System.out.println(lista1.get(1));
    }

}
