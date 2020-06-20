package com.edu.cedesistemas.oop.functional;

//Lección 4, Teller Reto functions, punto 2.

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
            return c -> MapCars(c);
        }

        private static Map<String, List<Car>> MapCars(List<Car> c) {
            Map<String, List<Car>> cars = new HashMap<>();
            for (Car carList : c) {
                List<Car> listCar = new ArrayList<Car>();
                for (Car carList2 : c) {
                    if (carList2.getName().equals(carList.getName())) {
                        listCar.add(carList2);
                    }
                    cars.put(carList.getName(), listCar);
                }
            }
            return cars;
        }

    }
