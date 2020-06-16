package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.geometry.Circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RetoCollections{

    RetoCollections(){
    }

    public static void main(String[] args) {
        RetoCollections collections = new RetoCollections();
        collections.listCircles();
        Map<Employee, String> map = collections.fillMap(collections.fillList());
    }


    //Circles
       public void listCircles(){
        List<Circle> circles = new ArrayList<Circle>();
        double random;
        do {
            random = new Random().nextDouble();
            circles.add(new Circle(random));
        } while (random > 0.01);
        for (Circle circle : circles) {
            System.out.println(circle.area());
        }
    }

        //Employee
        //Llenar la lista de empleados
        private List<Employee> fillList(){
            List<Employee> listEmployees = new ArrayList<>();
            listEmployees.add(new Employee("Pedro Sarmiento", "A1234"));
            listEmployees.add(new Employee("Juan Muñoz", "A1235"));
            listEmployees.add(new Employee("Clara Mendez", "A1236"));
            listEmployees.add(new Employee("Jorge Perdomo", "A1237"));
            listEmployees.add(new Employee("Maria Muñoz", "1238"));
            return listEmployees;
        }

        //Llenar el mapa con clave tipo Empleado y valor tipo String
        public Map<Employee, String> fillMap(List<Employee> listEmployees){
        Map<Employee, String> mapEmployee = new HashMap<>();
        for (Employee m : listEmployees){
            mapEmployee.put(m,m.getName());
            }
            return mapEmployee;
        }

        //Visualizar el mapa de Empleados (Usando Map.Entry)
        public void viewMap(Map<Employee, String> mapEmployye) {
            for (Map.Entry<Employee, String> e : mapEmployye.entrySet()) {
                System.out.println("ID:" + e.getKey().getId() + " Name:" + e.getKey().getName());
            }
        }


        //Numbers

}
