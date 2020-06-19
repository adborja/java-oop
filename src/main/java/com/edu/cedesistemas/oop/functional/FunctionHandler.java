package com.edu.cedesistemas.oop.functional;

import com.edu.cedesistemas.oop.model.geometryOK.Scalable;
import com.edu.cedesistemas.oop.model.vehicle.Car;

import java.lang.reflect.MalformedParameterizedTypeException;
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

    private static Map<String, List<Car>> getCars(List<Car> cars){
        Map<String, List<Car>> carMap = new HashMap<>();
        for (Car car : cars){
            List<Car> listCar = new ArrayList<>();
            for (Car car2: cars) {
                if (car2.getName().equals(car.getName())) {
                    listCar.add(car2);
                    carMap.put(car.getName(), listCar);
                }
            }
        }
        return carMap;
    }

    public static Function<List<Car>, Map<String, List<Car>>> getCarMapper() {
        return cars -> getCars(cars);
    }
}
