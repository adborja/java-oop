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
        Map<Employee, String> eMap = collections.fillEmployeeMap(collections.fillEmployeeList());
        collections.viewEMap(eMap);
        Map<NumberIE, String> nMap = collections.fillNumberMap(collections.fillListNumberName());
        collections.viewNMap(nMap);
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
    private List<Employee> fillEmployeeList(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Pedro Sarmiento", "A1234"));
        employeeList.add(new Employee("Juan Muñoz", "A1235"));
        employeeList.add(new Employee("Clara Mendez", "A1236"));
        employeeList.add(new Employee("Jorge Perdomo", "A1237"));
        employeeList.add(new Employee("Maria Muñoz", "1238"));
        employeeList.add(new Employee("Felipe Gomez", "1238"));
        return employeeList;
    }

    //Llenar el mapa con clave tipo Empleado y valor tipo String
    public Map<Employee, String> fillEmployeeMap(List<Employee> employeeList){
        Map<Employee, String> employeeMap = new HashMap<>();
        for (Employee m : employeeList){
            employeeMap.put(m,m.getName());
        }
        return employeeMap;
    }

    //Visualizar el mapa de Empleados (Usando Map.Entry)
    public void viewEMap(Map<Employee, String> employeeMap) {
        System.out.println("-----------Mapa Empleados--------------");
        for (Map.Entry<Employee, String> e : employeeMap.entrySet()) {
            System.out.println("ID: " + e.getKey().getId() + " Name: " + e.getKey().getName());
        }
        System.out.println("Cantidad Empleados: " + employeeMap.size());
        System.out.println();
    }


    //Numbers
    //Llenar la lista de NumberIE
    private List<NumberIE> fillListNumberName(){
        List<NumberIE> numberIEList = new ArrayList<>();
        numberIEList.add(new NumberIE(1,"Uno", "One"));
        numberIEList.add(new NumberIE(2, "Dos", "Two"));
        numberIEList.add(new NumberIE(3, "Tres", "Three"));
        numberIEList.add(new NumberIE(4,"Cuatro", "Four"));
        return numberIEList;
    }

    //Llenar el mapa con clave tipo NumberIE y valor tipo String
    public Map<NumberIE, String> fillNumberMap(List<NumberIE> numberIEList){
        Map<NumberIE, String> numberNameMap = new HashMap<>();
        for (NumberIE n: numberIEList){
            numberNameMap.put(n, n.getNameSpanish());
        }
        return numberNameMap;
    }

    //Visualizar el mapa de NumberIE (Usando Map.Entry)
    public void viewNMap(Map<NumberIE, String> numberNameMap) {
        System.out.println("-----------Mapa Números--------------");
        for (Map.Entry<NumberIE, String> n : numberNameMap.entrySet()) {
            System.out.println("Numero: " + n.getKey().getNumber() + " Español: " + n.getKey().getNameSpanish()
                    +" Inglés: " + n.getKey().getNameEnglish());
        }
        System.out.println("Cantidad Números: " + numberNameMap.size());
    }
}
