package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class CollectionsMain {
    public static void main(String[] args){
        Employee e1 = new Employee("1234", "Jhonatan Plata");
        Employee e2 = new Employee("1235", "Janina Rada");
        Employee e3 = new Employee("1236", "Yomaira Mendoza");
        Employee e4 = new Employee("1237", "Carolaine Plata");
        Employee e5 = new Employee("1238", "Victor Vasques");

        Map<Employee, String> employeeMap = new HashMap<>();
        employeeMap.put(e1, e1.getName());
        employeeMap.put(e2, e2.getName());
        employeeMap.put(e3, e3.getName());
        employeeMap.put(e4, e4.getName());
        employeeMap.put(e5, e5.getName());

        System.out.println(employeeMap.size());

    }
}
