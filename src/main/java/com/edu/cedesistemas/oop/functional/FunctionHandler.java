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


        Function<List<Car>, Map<String, List<Car>>> mapaCarros = s -> {
            Set<List> tipoCarros = new HashSet<>();
            tipoCarros.add(s);
            List<Car> carrosMazda = new ArrayList<>();
            List<Car> carrosRenault = new ArrayList<>();
            List<Car> carrosAudi = new ArrayList<>();
            List<Car> carrosMercedes = new ArrayList<>();
            List<Car> carrosFord = new ArrayList<>();

            for(List<Car> a : tipoCarros){

                if (a.getClass().getName() == "mazda")
                    for(Car b : s){
                        if (b.getName() == a.getClass().getName())
                            carrosMazda.add(b);
                    }

                if (a.getClass().getName() == "renault")
                    for(Car b : s){
                        if (b.getName() == a.getClass().getName())
                            carrosRenault.add(b);
                    }

                if (a.getClass().getName() == "audi")
                    for(Car b : s){
                        if (b.getName() == a.getClass().getName())
                            carrosAudi.add(b);
                    }

                if (a.getClass().getName() == "mercedes")
                    for(Car b : s){
                        if (b.getName() == a.getClass().getName())
                            carrosMercedes.add(b);
                    }

                if (a.getClass().getName() == "ford")
                    for(Car b : s){
                        if (b.getName() == a.getClass().getName())
                            carrosFord.add(b);
                    }

            }

            Map<String, List<Car>> mapa = new HashMap<>();
            for(List<Car> a : tipoCarros){

                if (a.getClass().getName() == "mazda")
                    mapa.put(a.getClass().getName(),carrosMazda);

                if (a.getClass().getName() == "renault")
                    mapa.put(a.getClass().getName(),carrosRenault);

                if (a.getClass().getName() == "audi")
                    mapa.put(a.getClass().getName(),carrosAudi);

                if (a.getClass().getName() == "mercedes")
                    mapa.put(a.getClass().getName(),carrosMercedes);

                if (a.getClass().getName() == "ford")
                    mapa.put(a.getClass().getName(),carrosFord);
            }
            return mapa;
        };

        return mapaCarros;
    }
}
