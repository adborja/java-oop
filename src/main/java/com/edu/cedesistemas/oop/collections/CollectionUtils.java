package com.edu.cedesistemas.oop.collections;

import com.edu.cedesistemas.oop.model.geometry.Shape;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CollectionUtils {
    public static <E> Set<E> removeDuplicates(Collection<E> s) {
        Set<E> uniques = new HashSet<>(s);
        return uniques;
    }

    public static <E> Set<E> union(Collection<E> s1, Collection<E> s2) {
        return null;
    }

    public static <E> Set<E> intersect(Collection<E> s1, Collection<E> s2) {
        return null;
    }
}