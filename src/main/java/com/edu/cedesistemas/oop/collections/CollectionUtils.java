package com.edu.cedesistemas.oop.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CollectionUtils {
    public static <E> Set<E> removeDuplicates(Collection<E> s) {

        return new HashSet<E>(s);
    }

    public static <E> Set<E> union(Collection<E> s1, Collection<E> s2) {
        Set<E> union = new HashSet<E>(s1);
        union.addAll(s2);
        return union;
    }

    public static <E> Set<E> intersect(Collection<E> s1, Collection<E> s2) {

        Set<E> intersection = new HashSet<E>(s1);
        intersection.retainAll(s2);
        return intersection;
    }
}