package com.edu.cedesistemas.oop.functional;

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
        return f.multiply(shape,value);
    }

    public static  <T> void consume(Consumer<List<T>> consumer, List<T> list) {
        consumer.accept(list);
    }

    public static Function<List<Car>, Map<String, List<Car>>> getCarMapper() {
        return cars -> listarCars(cars);
    }

        private static Map<String, List<Car>> listarCars(List<Car> cars) {
        Map<String, List<Car>> carMaps = new HashMap<>();
        for (Car c1: cars){
         carMaps.put(c1.getName(), nameCars(cars, c1.getName()));
        }
        return carMaps;
    }

        private static List<Car> nameCars(List<Car> cars, String name) {
        List<Car> carName = new ArrayList<>();
        for (Car c1: cars){
            if (c1.getName().equals(name)){
                carName.add(c1);
            }
        }
        return carName;
        }
    }
