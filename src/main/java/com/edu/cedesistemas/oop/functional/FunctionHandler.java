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
            return f.multiply(shape, value);
        }

        public static <T> void consume(Consumer<List<T>> consumer, List<T> list) {
            consumer.accept(list);
        }

        public static Function<List<Car>, Map<String, List<Car>>> getCarMapper() {
            return cars -> carsListToMap(cars);
        }

        private static Map<String, List<Car>> carsListToMap(List<Car> carsList) {
            Map<String, List<Car>> carsMap = new HashMap<>();
            for (Car car : carsList) {
                carsMap.put(car.getName(), getCarName(carsList, car.getName()));
            }
            return null;
        }

        private static List<Car> getCarName(List<Car> baseCars, String name) {
            List<Car> carsNameList = new ArrayList<>();
            for (Car car : baseCars) {
                if (car.getName().equals(name)) {
                    carsNameList.add(car);
                }
            }
            return carsNameList;
        }
    }
