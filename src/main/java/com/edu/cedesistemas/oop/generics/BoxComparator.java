package com.edu.cedesistemas.oop.generics;

import java.util.Comparator;

public class BoxComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T t, T t1) {
        return 0;
    }
}
