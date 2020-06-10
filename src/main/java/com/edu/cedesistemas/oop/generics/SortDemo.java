package com.edu.cedesistemas.oop.generics;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.PointComparator;
import com.edu.cedesistemas.oop.model.geometry.ReversePointComparator;
import com.edu.cedesistemas.oop.model.vehicle.Car;
import com.edu.cedesistemas.oop.model.vehicle.ElectricCar;
import com.edu.cedesistemas.oop.model.vehicle.FuelCar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortDemo {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(Arrays.asList(3, 8, 6, 4, 9, 2, 5, 1, 7));
        System.out.println("before sort integers: " + integers);
        Sorter.bubbleSort(integers);
        System.out.println("after sort integers: " + integers);

        List<Double> doubles = new ArrayList<>(Arrays.asList(1.3, 1.8, 1.6, 1.4, 1.9, 1.2, 1.5, 1.1, 1.7));
        System.out.println("before sort doubles: " + doubles);
        Sorter.bubbleSort(doubles);
        System.out.println("after sort doubles: " + doubles);

        List<Car> cars = new ArrayList<>();
        Car car1 = new ElectricCar(100, "mazda", 40);
        Car car2 = new FuelCar(80, "renault", 40, "2WD");
        cars.add(car1);
        cars.add(car2);

        System.out.println("before sort cars: " + cars);
        Sorter.bubbleSort(cars);
        System.out.println("after sort cars: " + cars);

        List<Point> points = new ArrayList<>(Arrays.asList(Point.of(5, 3),
                Point.of(10, 20), Point.of(74, -10)));

        PointComparator pointComparator = new PointComparator();
        System.out.println("before sort points: " + points);
        Sorter.bubbleSort(points, pointComparator);
        System.out.println("after sort points: " + points);

        ReversePointComparator reversePointComparator = new ReversePointComparator();
        System.out.println("before sort points: " + points);
        Sorter.bubbleSort(points, reversePointComparator);
        System.out.println("after sort points: " + points);

        List<Integer> integers1 = new ArrayList<>(Arrays.asList(5, 2, 8, 5, 1, 7, 4, 3));
        System.out.println("before sort integers: " + integers1);
        Sorter.mergeSort(integers1, 5,3);
        System.out.println("after sort integers: " + integers1);
    }
}
