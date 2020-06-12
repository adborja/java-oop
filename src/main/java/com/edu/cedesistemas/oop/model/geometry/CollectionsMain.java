package com.edu.cedesistemas.oop.model.geometry;

import com.edu.cedesistemas.oop.collections.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CollectionsMain {
    public static void main(String[] args) {

        List<Circle> circles = new ArrayList<>();
        double random;
        do {
            random = new Random().nextDouble();
            circles.add(new Circle(random));
        } while (random < 0.01);

        for (Circle circle : circles) {
            System.out.println(circle.area());
        }

        Map<Employee, String> employeeMap = new HashMap<>();
        Employee emp1 = new Employee("A1234", "Pedro Sarmiento");
        employeeMap.put(emp1, emp1.getName());
        Employee emp2 = new Employee("A1235", "Juan Muñoz");
        employeeMap.put(emp2, emp2.getName());
        Employee emp3 = new Employee("A1236", "Clara Mendez");
        employeeMap.put(emp3, emp3.getName());
        Employee emp4 = new Employee("A1237", "Jorge Perdomo");
        employeeMap.put(emp4, emp4.getName());
        Employee emp5 = new Employee("A1238", "Jorge Perdomo");
        employeeMap.put(emp5, emp5.getName());

        System.out.println(employeeMap.get(emp4));

       /* Map<String, Integer> numbersMap = new HashMap<>();
        numbersMap.put("one", 1);
        numbersMap.put("uno", 1);
        numbersMap.put("two", 2);
        numbersMap.put("dos", 2);
        numbersMap.put("three", 3);
        numbersMap.put("tres", 3);
        numbersMap.put("four", 4);
        numbersMap.put("cuatro", 4);
        numbersMap.put("five", 5);
        numbersMap.put("cinco", 5);


        for (Map.Entry<String, Integer> e : numbersMap.entrySet()) {
            if(e.getValue().equals(5))
            System.out.println(e.getKey());
        }*/

        Map<Integer, String> numbersMap2 = new HashMap<>();

        numbersMap2.put(1,"uno");
        numbersMap2.put(1,"one");
        numbersMap2.put(1,"el primero");

        for (Map.Entry<Integer, String> e : numbersMap2.entrySet()) {
            if(e.getKey().equals(1))
                System.out.println(e.getValue());
        }



    }


}
