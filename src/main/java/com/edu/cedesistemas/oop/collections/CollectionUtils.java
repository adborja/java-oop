package com.edu.cedesistemas.oop.collections;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//Taller Lección 4 – Collections
public class CollectionUtils {
    public static <E> Set<E> removeDuplicates(Collection<E> s) {
        Set<E> uniques = new HashSet<>(s);

        return uniques;
    }

    public static <E> Set<E> union(Collection<E> s1, Collection<E> s2) {
        Set<E> set1 = new HashSet<>(s1);
        Set<E> set2 = new HashSet<>(s2);
        Set<E> all = new HashSet<>();

        all.addAll(set1);
        all.addAll(set2);

        return all;
    }


    public static <E> Set<E> intersect(Collection<E> s1, Collection<E> s2) {
        Set<E> set1 = new HashSet<>(s1);
        Set<E> set2 = new HashSet<>(s2);

        Set<E> intersect = new HashSet<>();
        intersect.addAll(set2);
        intersect.retainAll(set1);

        return intersect;
    }
}