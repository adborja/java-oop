package com.edu.cedesistemas.oop.streams;

import com.edu.cedesistemas.oop.model.geometryOK.Circle;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        /*
        Circle c1 = new Circle(20);
        Circle c2 = new Circle(20);
        Circle c3 = new Circle(20);
        Circle c4 = new Circle(20);
        Circle c5 = new Circle(20);

        List<Circle> circles = Arrays.asList(c1, c2, c3, c4, c5);
         */
        demoStreamFromArray();
        demoStreamFromList();
        demoChained();
        demoMatch();
        demoFilter();
        demoMap();
        demoReduction();
        demoCollect();
    }

    private static void demoStreamFromArray() {
        System.out.println("********* demo stream from array **********");
        String[] array = {"vaso", "cuchara", "cuchillo", "tenedor", "plato", "sarten", "servilleta", "mantel"};
        Stream<String> stream = Stream.of(array);
        Consumer<String> consumer = s -> System.out.println(s);
        stream.forEach(consumer);

        stream = Arrays.stream(array);
        stream.forEach(s -> System.out.println(s));
    }

    private static void demoStreamFromList() {
        System.out.println("********* demo stream from list **********");
        List<String> list = Arrays.asList("vaso", "cuchara", "cuchillo", "tenedor", "plato",
                "sarten", "servilleta", "mantel");
        Stream<String> stream = list.stream();
        stream.forEach(s -> System.out.println(s));
    }

    private static void demoChained() {
        System.out.println("********* demo stream chained **********");
        List<String> list = Arrays.asList("vaso", "cuchara", "cuchillo", "tenedor", "plato",
                "sarten", "servilleta", "mantel");

        long size = list.stream()
                .distinct()
                .count();
        System.out.println("Elementos distintos: " + size);
    }

    private static void demoMatch() {
        System.out.println("********* demo match **********");
        List<String> list = Arrays.asList("vaso", "cuchara", "cuchillo", "tenedor", "plato",
                "sarten", "servilleta", "mantel");
        boolean exists = list.stream()
                .anyMatch(l -> l.contains("cuchara"));
        System.out.println("exists: " + exists);
    }

    private static void demoFilter() {
        System.out.println("********* demo filter **********");
        List<String> list = Arrays.asList("vaso", "cuchara", "cuchillo", "tenedor", "plato",
                "sarten", "servilleta", "mantel");
        long count = list.stream().filter(s -> s.startsWith("c")).count();
        System.out.println("count: " + count);
    }

    private static void demoMap() {
        System.out.println("********* demo map **********");
        List<String> list = Arrays.asList("vaso", "cuchara", "cuchillo", "tenedor", "plato",
                "sarten", "servilleta", "mantel");
        Stream<Integer> lengths = list.stream().map(s -> s.length());
        lengths.forEach(i -> System.out.println(i));
    }

    private static void demoReduction() {
        System.out.println("********* demo reduction **********");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Integer result = list.stream().reduce(10, (a, b) -> Integer.sum(a, b));
        System.out.println("Result: " + result);
    }

    private static void demoCollect() {
        System.out.println("********* demo collect **********");
        List<String> list = Arrays.asList("vaso", "cuchara", "cuchillo", "tenedor", "plato",
                "sarten", "servilleta", "mantel");
        List<String> newList = list.stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        newList.forEach(s -> System.out.println(s));
    }
}
