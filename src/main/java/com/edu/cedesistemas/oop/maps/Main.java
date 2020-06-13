package com.edu.cedesistemas.oop.maps;

import java.util.HashMap;
import java.util.Map;
//Taller Lección 4 – Collections, Ejercicio 2
public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("A1234", "Pedro Sarmiento");
        Employee e2 = new Employee("A1235", "Juan Muñoz");
        Employee e3 = new Employee("A1236", "Clara Mendez");
        Employee e4 = new Employee("A1237", "Jorge Perdomo");
        Employee e5 = new Employee("A1238", "Maria Nuñez");

        Map<Employee, String> employeeStringMap = new HashMap<>();
        employeeStringMap.put(e1, e1.getName());
        employeeStringMap.put(e2, e2.getName());
        employeeStringMap.put(e3, e3.getName());
        employeeStringMap.put(e4, e4.getName());
        employeeStringMap.put(e5, e5.getName());

    }
}
