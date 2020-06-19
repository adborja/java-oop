package com.edu.cedesistemas.oop.functional;

import com.edu.cedesistemas.oop.model.geometry.Scalable;
import com.edu.cedesistemas.oop.model.vehicle.Car;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

    public class FunctionHandler {
    public static <T, R> R applyFunction(Function<T, R> f, T t) {

        return f.apply(t);
    }

    public static <T extends Scalable<T, U>, U extends Number> T getMultiplied(ShapeMultiplier<T, U> f, T shape, U value) {
        return null;
    }

    public static  <T> void consume(Consumer<List<T>> consumer, List<T> list) {

    }

    public static Function<List<Car>, Map<String, List<Car>>> getCarMapper() {
        return null;
    }
}
