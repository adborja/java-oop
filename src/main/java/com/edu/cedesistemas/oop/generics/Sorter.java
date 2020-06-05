package com.edu.cedesistemas.oop.generics;

import java.util.List;

public class Sorter {
    public static <T extends Comparable<T>> void bubbleSort(List<T> array) {
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j).compareTo(array.get(j + 1)) > 0) {
                    T temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
    }

    // Implement generic mergeSort

}
