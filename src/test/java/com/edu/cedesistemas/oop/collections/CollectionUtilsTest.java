package com.edu.cedesistemas.oop.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import com.edu.cedesistemas.oop.model.geometryOK.Circle;
import com.edu.cedesistemas.oop.model.geometryOK.Ellipse;
import com.edu.cedesistemas.oop.model.geometryOK.Shape;
import com.edu.cedesistemas.oop.model.vehicle.Car;
import com.edu.cedesistemas.oop.model.vehicle.ElectricCar;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CollectionUtilsTest {
    @Test
    public void testRemoveDuplicates() {
        // Creando instancias de Ellipse ...
        Shape s1 = new Ellipse(5, 10);
        Shape s2 = new Ellipse(6, 8);
        Shape s3 = new Ellipse(10, 15);
        Shape s4 = new Ellipse(5, 10);
        Shape s5 = new Ellipse(10, 5);
        Shape s6 = new Ellipse(6, 8);

        // Creando una lista de figuras ...
        Collection<Shape> shapes = Arrays.asList(s1, s2, s3, s4, s5, s6);

        //System.out.println("s1 area: " + s1.area());
        //System.out.println("s1 perimeter: " + s1.perimeter());
        //System.out.println("s2 area: " + s2.area());
        //System.out.println("s2 perimeter: " + s2.perimeter());
        //System.out.println("s3 area: " + s3.area());
        //System.out.println("s2 perimeter: " + s3.perimeter());
        //System.out.println("s4 area: " + s4.area());
        //System.out.println("s4 perimeter: " + s4.perimeter());
        //System.out.println("s5 area: " + s5.area());
        //System.out.println("s5 perimeter: " + s5.perimeter());
        //System.out.println("s6 area: " + s6.area());
        //System.out.println("s6 perimeter: " + s6.perimeter());

        //System.out.println("Total Prueba: " + new HashSet<Shape>(shapes).size());

        // Removiendo duplicados: Una elipse es igual a otra siempre y cuando los valores de a y de b sean iguales
        Set<Shape> shapesResult = CollectionUtils.removeDuplicates(shapes);

        //System.out.println("Tamaño: " + shapesResult.size());
        // Verificando que se hayan eliminado las figuras repetidas sin importar el order
        assertThat(shapesResult, containsInAnyOrder(s1, s2, s3, s5)); // no importa el orden


        // Creando instancias de ElectricCar y asignado IDs
        ElectricCar c1 = new ElectricCar(80, "mazda", 50);
        c1.setId("101");

        ElectricCar c2 = new ElectricCar(85, "ford", 45);
        c2.setId("102");

        ElectricCar c3 = new ElectricCar(90, "mazda", 40);
        c3.setId("101");

        ElectricCar c4 = new ElectricCar(100, "audi", 35);
        c4.setId("104");

        // Creando una lista de carros electricos ...
        Collection<Car> cars = Arrays.asList(c1, c2, c3, c4);

        // Removiendo duplicados: Un carro es igual a otro cuando sus IDs son iguales
        Set<Car> carResult = CollectionUtils.removeDuplicates(cars);

        // Verificando que se hayan eliminado los duplicados sin importar el órden
        assertThat(carResult, containsInAnyOrder(c1, c2, c4));
    }

    @Test
    public void testUnion() {
        // Creando primeras figuras
        Collection<Shape> shapes1 = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Shape s = new Circle(10 * i);
            shapes1.add(s);
        }

        // Creando otra lista de figuras ...
        Collection<Shape> shapes2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Shape s = new Circle(10 * (i + 1));
            shapes2.add(s);
        }

        // Agregando otros 10 elementos a la lista
        for (int i = 10; i < 20; i++) {
            Shape s = new Circle(10 * (i + 1));
            shapes2.add(s);
        }

        Set<Shape> result = CollectionUtils.union(shapes1, shapes2);
        assertThat(result.size(), equalTo(20));
    }

    @Test
    public void testIntersect() {
        ElectricCar c1 = new ElectricCar(1, "a", 10);
        c1.setId("1");

        ElectricCar c2 = new ElectricCar(1, "a", 10);
        c2.setId("2");

        ElectricCar c3 = new ElectricCar(1, "a", 10);
        c3.setId("3");

        ElectricCar c4 = new ElectricCar(1, "a", 10);
        c4.setId("4");

        ElectricCar c5 = new ElectricCar(1, "a", 10);
        c5.setId("5");

        Collection<ElectricCar> cars1 = Arrays.asList(c1, c2, c3, c4, c5);

        ElectricCar c6 = new ElectricCar(1, "a", 10);
        c6.setId("6");

        ElectricCar c7 = new ElectricCar(1, "a", 10);
        c7.setId("7");

        ElectricCar c8 = new ElectricCar(1, "a", 10);
        c8.setId("1");

        ElectricCar c9 = new ElectricCar(1, "a", 10);
        c9.setId("2");

        ElectricCar c10 = new ElectricCar(1, "a", 10);
        c10.setId("10");

        Collection<ElectricCar> cars2 = Arrays.asList(c6, c7, c8, c9, c10);

        Set<? extends Car> result = CollectionUtils.intersect(cars1, cars2);
        assertThat(result, containsInAnyOrder(c8, c9));
    }
}
