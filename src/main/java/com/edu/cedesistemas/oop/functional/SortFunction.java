package com.edu.cedesistemas.oop.functional;

import java.util.List;

@FunctionalInterface
public interface SortFunction<T extends Comparable<T>> {
    void sort(List<T> list);
}
