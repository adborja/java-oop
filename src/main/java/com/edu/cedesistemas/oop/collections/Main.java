package com.edu.cedesistemas.oop.collections;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args){

        Employee e1 = new Employee("A1234", "Pedro Sarmiento" );
        Employee e2 = new Employee("A1235", "Juan Muñoz" );
        Employee e3 = new Employee("A1236", "Clara Mendez" );
        Employee e4 = new Employee("A1237", "Jorge Perdomo" );
        Employee e5 = new Employee("A1238", "María Nuñez" );

        Map<Employee, String> mp = new HashMap<>();
        mp.put(e1, e1.getNombre());
        mp.put(e2, e2.getNombre());
        mp.put(e3, e3.getNombre());
        mp.put(e4, e4.getNombre());
        mp.put(e5, e5.getNombre());

        System.out.println(mp.size());

        Numeros n1 = new Numeros(1, "uno", "one" );
        Numeros n2 = new Numeros(2,"dos", "two" );
        Numeros n3 = new Numeros(3, "tres", "three" );

        Map<Integer, Numeros> mn = new HashMap<>();

    }
}
