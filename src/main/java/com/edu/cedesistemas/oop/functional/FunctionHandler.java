package com.edu.cedesistemas.oop.functional;

import com.edu.cedesistemas.oop.model.geometry.CircleRadiusComparator;
import com.edu.cedesistemas.oop.model.geometry.Scalable;
import com.edu.cedesistemas.oop.model.vehicle.Car;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionHandler {
    public static <T, R> R applyFunction(Function<T, R> f, T t) {
        return f.apply(t);
    }

    public static <T extends Scalable<T, U>, U extends Number> T getMultiplied(ShapeMultiplier<T, U> f, T shape, U value) {
        return f.multiply(shape,value);
    }

    public static  <T> void consume(Consumer<List<T>> consumer, List<T> list) {
        consumer.accept(list);
    }

    public static Function<List<Car>, Map<String, List<Car>>> getCarMapper() {
            return cars -> listarCars(cars);
    }
    private static Map<String, List<Car>> listarCars(List<Car> cars) {
        Map<String, List<Car>> carsMap = new HashMap<>();
        for (Car a : cars) {
            carsMap.put(a.getName(), nombreCarros(cars, a.getName()));
        }
        return carsMap;
    }

    private static List<Car> nombreCarros(List<Car> originalCars, String name) {
        List<Car> carrosPorNombre = new ArrayList<>();
        for (Car a : originalCars) {
            if (a.getName().equals(name)) {
                carrosPorNombre.add(a);
            }
        }
        return carrosPorNombre;
    }
}
