package com.edu.cedesistemas.oop.generics;

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
    }
}
