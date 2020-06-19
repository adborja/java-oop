package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.geometry.Circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Collections  {

    public Collections(){

    }

    /**
     Punto 1. Circles
     **/
    public void listCircles(){
        List<Circle> circles = new ArrayList<>();
        double random=10;
        while (random > 0.001){
            random = new Random().nextDouble();
            circles.add(new Circle(random));
        }

        for(Circle circle: circles){
            System.out.println("Area del Circulo: " + circle.area());
        }
    }

    /**
     Punto 2. Map Employee
     **/
    public Map<Employee, String> loadMap(List<Employee> employees){
        Map<Employee, String> map = new HashMap<Employee, String>();
        for (Employee m: employees){
            map.put(m , m.getName());
        }
        return map;
    }

    public void listMaps(Map<Employee, String> map){
        for (Map.Entry<Employee, String> key : map.entrySet()){
            System.out.println("ID: " + key.getKey().getId() + " > Name: " + key.getValue());
        }
    }

    public void consultEmployeeMap(Map<Employee, String> map, String id){
        for (Map.Entry<Employee, String> key : map.entrySet()){
            if (key.getKey().getId().equals(id)) {
                System.out.println("Employee con ID: " + key.getKey().getId() + " y Name: " + key.getValue() + " fue encontrado.");
                return;
            }
        }
        System.out.println("Employee con ID " + id + " NO fue Encontrado.");
    }

    private List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee emp = new Employee("A1234", "Pedro Sarmiento");
        employees.add(emp);
        emp = new Employee("A1235", "Juan Muñoz");
        employees.add(emp);
        emp = new Employee("A1236", "Clara Mendez");
        employees.add(emp);
        emp = new Employee("A1237", "Jorge Perdomo");
        employees.add(emp);
        emp = new Employee("A1238", "María Nuñez");
        employees.add(emp);
        emp = new Employee("A1238", "Juan Perez");
        employees.add(emp);
        emp = new Employee("A12311", "María Nuñez 11");
        employees.add(emp);
        return employees;
    }

    public void testMaps(){
        Map<Employee, String> map = loadMap(getEmployees());
        listMaps(map);
        System.out.println("--------2.1. Consulta con Id en Mapa de Objetos Employee--------");
        consultEmployeeMap(map, "A1234");
        consultEmployeeMap(map, "A3289");
        consultEmployeeMap(map, "A4646");
        consultEmployeeMap(map, "27646");
        consultEmployeeMap(map, "A1238");
        consultEmployeeMap(map, "A36536");
    }

    /**
     Punto 3. Map Number
     **/

    public Map<NumberSE, String> loadMapNumber(List<NumberSE> numbers){
        Map<NumberSE, String> map = new HashMap<NumberSE, String>();
        for (NumberSE n: numbers){
            map.put(n , n.getNameSpanish());
        }
        return map;
    }

    private List<NumberSE> getNumbers(){
        List<NumberSE> numbers = new ArrayList<>();
        NumberSE n = new NumberSE(1, "Uno", "One");
        numbers.add(n);
        n = new NumberSE(2, "Dos", "Two");
        numbers.add(n);
        n = new NumberSE(3, "Tres", "Three");
        numbers.add(n);
        n = new NumberSE(3, "Tres", "Three");
        numbers.add(n);
        n = new NumberSE(4, "Cuatro", "Four");
        numbers.add(n);
        n = new NumberSE(5, "Cinco", "Five");
        numbers.add(n);
        return numbers;
    }

    private void consultNumberMap(Map<NumberSE, String> map, Integer number){
        for (Map.Entry<NumberSE, String> key : map.entrySet()){
            if (key.getKey().getNumber().equals(number)){
                System.out.println("Number: " + key.getKey().getNumber() +
                        ", Spanish: " + key.getValue() + ", English: " + key.getKey().getNameEnglish());
                return;
            }
        }
        System.out.println("No fue encontrado el numero: " + number);
    }

    public void testNumbers(){
        Map<NumberSE, String> map = loadMapNumber(getNumbers());
        consultNumberMap(map, 1);
        consultNumberMap(map, 2);
        consultNumberMap(map, 8);
        consultNumberMap(map, 3);
        consultNumberMap(map, 9);
        consultNumberMap(map, 4);
        consultNumberMap(map, 5);
        consultNumberMap(map, 6);
    }

    public static void main(String[] args) {
        Collections collections = new Collections();
        System.out.println("--------1. Lista de Objetos Circle--------");
        collections.listCircles();
        System.out.println("--------2. Mapa de Objetos Employee--------");
        collections.testMaps();
        System.out.println("--------3. Mapa de Numeros--------");
        collections.testNumbers();
        System.out.println("--------4. Pruebas--------");
    }

}
