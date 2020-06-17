package com.edu.cedesistemas.oop.functional;

import com.edu.cedesistemas.oop.model.geometry.Circle;
import com.edu.cedesistemas.oop.model.geometry.Scalable;
import com.edu.cedesistemas.oop.model.vehicle.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

    public class FunctionHandler {
    public static <T, R> R applyFunction(Function<T, R> f, T t) {
        return f.apply(t);
    }

    public static <T extends Scalable<T, U>, U extends Number> T getMultiplied(ShapeMultiplier<T, U> f, T shape, U value) {
        return f.multiply(shape, value);
    }

    public static  <T> void consume(Consumer<List<T>> consumer, List<T> list) {
        consumer.accept(list);
    }

    public static Function<List<Car>, Map<String, List<Car>>> getCarMapper() {
        return cars -> listToMap(cars);
    }
    private static Map<String, List<Car>> listToMap(List<Car> cars) {
        Map<String, List<Car>> carsMap = new HashMap<>();
        for (Car car : cars) {
            carsMap.put(car.getName(), getCarsByName(cars, car.getName()));
        }
        return carsMap;
    }

    private static List<Car> getCarsByName(List<Car> originalCars, String name) {
        List<Car> carsGroupByName = new ArrayList<>();
        for (Car car : originalCars) {
            if (car.getName().equals(name)) {
                carsGroupByName.add(car);
            }
        }
        return carsGroupByName;
    }
}
