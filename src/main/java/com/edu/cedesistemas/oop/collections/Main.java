package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.Employee;
<<<<<<< HEAD
import com.edu.cedesistemas.oop.model.geometry.Shape;
import com.sun.javafx.collections.MappingChange;
=======
>>>>>>> 362d7d74e76413dced2ba44ba6dae5d4c7f9ab4b

import java.util.HashMap;
import java.util.Map;

public class Main {
<<<<<<< HEAD
    public static void  main(String[] args){
=======
    public static void main(String[] args) {
>>>>>>> 362d7d74e76413dced2ba44ba6dae5d4c7f9ab4b
        Employee e1 = new Employee("A1234", "Pedro Sarmiento");
        Employee e2 = new Employee("A1235", "Juan Muñoz");
        Employee e3 = new Employee("A1236", "Clara Mendez");
        Employee e4 = new Employee("A1237", "Jorge Perdomo");
<<<<<<< HEAD
        Employee e5 = new Employee("A1238", "Maria Nuñez");
        Employee e6 = new Employee("A1238", "Maria velez");
=======
        Employee e5 = new Employee("A1238", "María Nuñez");
>>>>>>> 362d7d74e76413dced2ba44ba6dae5d4c7f9ab4b

        Map<Employee, String> employeeMap = new HashMap<>();
        employeeMap.put(e1, e1.getName());
        employeeMap.put(e2, e2.getName());
        employeeMap.put(e3, e3.getName());
        employeeMap.put(e4, e4.getName());
        employeeMap.put(e5, e5.getName());
<<<<<<< HEAD
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
=======

        System.out.println(employeeMap.size());

        Employee e6 = new Employee("A1234", "Carlos Fuentes");
        employeeMap.put(e6, e6.getName());

        System.out.println("Nuevo tamaño: " + employeeMap.size());
    }
}
>>>>>>> 362d7d74e76413dced2ba44ba6dae5d4c7f9ab4b
