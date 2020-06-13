package com.edu.cedesistemas.oop.functional;

import com.edu.cedesistemas.oop.generics.Sorter;
import com.edu.cedesistemas.oop.model.geometry.Circle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ordenar numeros ...
        List<Integer> numbers = Arrays.asList(5, 8, 2, 10, 4, 6, 1);
        // Solucion 1: Usando clase anonima
        SortFunction<Integer> sorter1 = new SortFunction<>() {
            @Override
            public void sort(List<Integer> list) {
                Collections.sort(list);
            }
        };
        sorter1.sort(numbers);
        System.out.println(numbers);

        // Solucion 2: Usando expresión lambda:
        SortFunction<Integer> sorter2 = l -> Sorter.bubbleSort(numbers);
        sorter2.sort(numbers);
        System.out.println(numbers);

        // EJERCICIO:
        Circle circle = new Circle(30);
        // ************ CAMBIAR ESTAS LINEAS POR EXPRESION LAMBDA **********************
        ShapeMultiplier<Circle, Double> shapeMultiplier = new ShapeMultiplierImpl<>();

        Circle newCircle = shapeMultiplier.multiply(circle, 200D);
        double area = newCircle.area();
        System.out.println("new area: " + area);
        // *****************************************************************************

        // INSERTE EXPRESION LAMBDA ACA PARA OBTENER EL MISMO RESULTADO
        ShapeMultiplier<Circle, Double> shapeMultiplierLambda = (scalable, value) -> scalable.scale(value);
        Circle newCircleLambda = shapeMultiplier.multiply(circle, 200D);
        double areaLambda = newCircle.area();
        System.out.println("new area Lambda: " + areaLambda);
        // ....
    }
}