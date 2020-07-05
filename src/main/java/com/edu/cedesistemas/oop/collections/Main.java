package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("A1234", "Pedro Sarmiento");
        Employee e2 = new Employee("A1235", "Juan Muñoz");
        Employee e3 = new Employee("A1236", "Clara Mendez");
        Employee e4 = new Employee("A1237", "Jorge Perdomo");
        Employee e5 = new Employee("A1238", "María Nuñez");

        Map<Employee, String> employeeMap = new HashMap<>();
        employeeMap.put(e1, e1.getName());
        employeeMap.put(e2, e2.getName());
        employeeMap.put(e3, e3.getName());
        employeeMap.put(e4, e4.getName());
        employeeMap.put(e5, e5.getName());

        System.out.println(employeeMap.size());

        Employee e6 = new Employee("A1234", "Carlos Fuentes");
        employeeMap.put(e6, e6.getName());

        System.out.println("Nuevo tamaño: " + employeeMap.size());



        }

    }

