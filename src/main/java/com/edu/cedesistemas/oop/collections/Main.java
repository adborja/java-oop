package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.Employee;
import com.edu.cedesistemas.oop.model.geometry.Shape;
import com.sun.javafx.collections.MappingChange;


import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void  main(String[] args){


        Employee e1 = new Employee("A1234", "Pedro Sarmiento");
        Employee e2 = new Employee("A1235", "Juan Muñoz");
        Employee e3 = new Employee("A1236", "Clara Mendez");
        Employee e4 = new Employee("A1237", "Jorge Perdomo");

        Employee e5 = new Employee("A1238", "Maria Nuñez");
        Employee e6 = new Employee("A1238", "Maria velez");


        Map<Employee, String> employeeMap = new HashMap<>();
        employeeMap.put(e1, e1.getName());
        employeeMap.put(e2, e2.getName());
        employeeMap.put(e3, e3.getName());
        employeeMap.put(e4, e4.getName());
        employeeMap.put(e5, e5.getName());

        employeeMap.put(e6, e6.getName());

        System.out.println(employeeMap.size());
        System.out.println("Nuevo Tamaño"+employeeMap.size());

        Map<String, Integer> numberMap = new HashMap<String, Integer>();
        numberMap.put("Uno",1);
        numberMap.put("One", 1);
        numberMap.put("Dos", 2);
        numberMap.put("Two",2);
        numberMap.put("Tres", 3);
        numberMap.put("Three",3);
        for(Map.Entry<String,Integer> num:numberMap.entrySet()){
            if(num.getValue().equals(1)) {
                System.out.println("Key: " + num.getKey()+" - Valor: " + num.getValue());
            }
        }
        for(Map.Entry<String,Integer> num:numberMap.entrySet()){
            if(num.getValue().equals(3)) {
                System.out.println("Key: " + num.getKey()+" - Valor: " + num.getValue());
            }
        }
    }

    }

