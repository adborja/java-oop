package com.edu.cedesistemas.oop.maps;

import java.util.HashMap;
import java.util.Map;
//Taller Lección 4 – Collections, Ejercicio 2
public class EmployeeMap {
    //Primera iteración del ejercicio, corregido en la clase Employee y ejecución en clase Main
    public static void main(String[] args) {
        Map<String, String> employee = new HashMap<>();
        employee.put("A1234", "Pedro Sarmiento");
        employee.put("A1235", "Juan Muñoz");
        employee.put("A1236", "Clara Mendez");
        employee.put("A1237", "Jorge Perdomo");
        employee.put("A1238", "Maria Nuñez");

        System.out.println(employee.get("A1234"));
        System.out.println(employee.get("A1236"));
        System.out.println(employee.get("A1237"));
        System.out.println(employee.get("A1238"));

    }
}
