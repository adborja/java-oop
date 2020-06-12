package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.geometry.Circle;
import com.edu.cedesistemas.oop.model.vehicles.ElectricCar;

import java.awt.font.NumericShaper;
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

    public Map<Number, String> loadMapNumber(List<Number> numbers){
        Map<Number, String> map = new HashMap<Number, String>();
        for (Number n: numbers){
            map.put(n , n.getNameSpanish());
        }
        return map;
    }

    public Map<Object, String> loadMapNumberGen(List<?> numbers1){
        Map<Object, String> map = new HashMap<Object, String>();
        for (Object n: numbers1){
            map.put(n, numbers1.get(0).toString());
        }
        return map;
    }

    private List<Number> getNumbers(){
        List<Number> numbers = new ArrayList<>();
        Number n = new Number(1, "Uno", "One");
        numbers.add(n);
        n = new Number(2, "Dos", "Two");
        numbers.add(n);
        n = new Number(3, "Tres", "Three");
        numbers.add(n);
        n = new Number(4, "Cuatro", "Four");
        numbers.add(n);
        n = new Number(5, "Cinco", "Five");
        numbers.add(n);
        return numbers;
    }

    private void consultNumberMap(Map<Number, String> map, Integer number){
        for (Map.Entry<Number, String> key : map.entrySet()){
            if (key.getKey().getNumber().equals(number)){
                System.out.println("Number: " + key.getKey().getNumber() +
                        ", Spanish: " + key.getValue() + ", English: " + key.getKey().getNameEnglish());
                return;
            }
        }
        System.out.println("No fue encontrado el numero: " + number);
    }

    public void testNumbers(){
        Map<Number, String> map = loadMapNumber(getNumbers());
        consultNumberMap(map, 1);
        consultNumberMap(map, 2);
        consultNumberMap(map, 8);
        consultNumberMap(map, 3);
        consultNumberMap(map, 9);
        consultNumberMap(map, 4);
        consultNumberMap(map, 5);
        consultNumberMap(map, 6);
    }

    public void testNumbersGen(){
        Map<?, String> map = loadMapNumberGen(getNumbers());
        consultNumberMapGen(map, 1);
        consultNumberMapGen(map, 2);
        consultNumberMapGen(map, 8);
        consultNumberMapGen(map, 3);
        consultNumberMapGen(map, 9);
        consultNumberMapGen(map, 4);
        consultNumberMapGen(map, 5);
        consultNumberMapGen(map, 6);
    }

    private void consultNumberMapGen(Map<?, String> map, Integer number){
        for (Map.Entry<?, String> key : map.entrySet()){
            /**if (key.getKey().getNumber().equals(number)){
                System.out.println("Number: " + key.getKey().getNumber() +
                        ", Spanish: " + key.getValue() + ", English: " + key.getKey().getNameEnglish());
                return;
            }**/
            //Number n;
            System.out.println("1. *********************************************");
            //System.out.println(key.getKey().getClass());
            System.out.println(key.getValue());
            System.out.println("1.1 *********************************************");
        }
        System.out.println("No fue encontrado el numero: " + number);
        System.out.println("2. *********************************************");
    }

    public static void main(String[] args) {
        Collections collections = new Collections();
        System.out.println("--------1. Lista de Objetos Circle--------");
        collections.listCircles();
        System.out.println("--------2. Mapa de Objetos Employee--------");
        collections.testMaps();
        System.out.println("--------3. Mapa de Numeros--------");
        collections.testNumbers();
        //System.out.println("--------3.1. Mapa de Numeros con Wildcars--------");
        //collections.testNumbersGen();
    }

}
