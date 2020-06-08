package com.edu.cedesistemas.oop.generics;

import com.edu.cedesistemas.oop.arrays.ArrayUtils;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sorter {
    /**
     * Método que ordena una lista genérica usando la técnica de
     * ordenamiento por burbuja
     */
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

    public static <T> void bubbleSort(List<T> array, Comparator<T> comparator) {
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(array.get(j), array.get(j + 1)) > 0) {
                    T temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
    }

    // Implement generic mergeSort

    public static <T extends Comparable<T>> void mergeSort(List<T> array, int n) {
        if (n < 2) {
            return;
        }
        int  mid = n / 2;

        List<T> l =  new ArrayList<>();
        List<T> r = new ArrayList<>();


        for (int i = 0; i < mid; i++) {
            l.add(array.get(i));
        }

        for (int i = mid; i < n; i++) {
            r.add(i - mid, array.get(i));
        }

        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(array, l, r, mid, n - mid);
    }


    private static <T extends Comparable<T>> void merge(List<T> a, List<T> l, List<T> r, int left, int right) {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (l.get(i).compareTo(r.get(j)) <= 1) {
                a.set(k++, l.get(i++));
            } else {
                a.set(k++, r.get(j++));
            }
            while (i < left) {
                a.set(k++, l.get(i++));
            }
            while (j < right) {
                a.set(k++, r.get(j++));
            }
        }
    }

}
