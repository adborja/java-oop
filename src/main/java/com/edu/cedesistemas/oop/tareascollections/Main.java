package com.edu.cedesistemas.oop.tareascollections;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tarea Collection!");

        ListCircles a = new ListCircles();
        a.listCircles();
        List<Employee> c = new ArrayList<>();

        c.add(new Employee("A1234","prueba repetido"));
        c.add(new Employee("A1234","Pedro Sarmiento"));
        c.add(new Employee("A1235","Juan Muñoz"));
        c.add(new Employee("A1236","Clara Mendez"));
        c.add(new Employee("A1237","Jorge Perdomo"));
        c.add(new Employee("A1238","María Nuñez"));

        TareaMapas b = new TareaMapas();
        b.tareaMapas(c);

        Map<String,String> m = new LinkedHashMap<>();
        m.put("uno","one");
        m.put("dos","two");
        m.put("tres","three");
        m.put("cuatro","four");
        m.put("cinco","five");
        TareaMapas e = new TareaMapas();
        e.numerosEnteros(m,5);
    }
}