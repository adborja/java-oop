package com.edu.cedesistemas.oop.arrays;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListSample {
    public static void iterateArrayByForLoop(List list) {
        System.out.println("------ Using for loop ------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Element: " + list.get(i));
        }
    }

    public static void iterateArrayByForEach(List list) {
        System.out.println("------ Using for each loop ------");
        for (Object s : list) {
            System.out.println("Element: " + s);
        }
    }

    public static void iterateArrayByIterator(List list) {
        System.out.println("------ Using iterator ------");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
             System.out.println("Element: " + iterator.next());
        }
    }

    public static void iterateArrayByListIterator(List list) {
        System.out.println("------ Using list iterator ------");
        ListIterator iterator = list.listIterator(list.size());

        // Iterating the list in reverse order ...
        while(iterator.hasPrevious()) {
            Object o = iterator.previous();
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        List myList = new ArrayList();
        myList.add("mazda");
        myList.add("ford");
        myList.add("renault");
        myList.add("audi");
        myList.add("chevrolet");

        iterateArrayByForLoop(myList);
        iterateArrayByForEach(myList);
        iterateArrayByIterator(myList);
        iterateArrayByListIterator(myList);
    }
}
