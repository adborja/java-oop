package com.edu.cedesistemas.oop.arrays;

import java.util.ArrayList;
import java.util.List;

public class AutoboxingSample {
    public static void main(String[] args) {
        // Sin autoboxing ...
        List<Integer> li1 = new ArrayList<>();
        for (int i = 1; i < 50; i += 2) {
            li1.add(Integer.valueOf(i));
        }

        // Con autoboxing ...
        List<Integer> li2 = new ArrayList<>();
        for (int i = 1; i < 50; i += 2) {
            li2.add(i); // El compilador hace autoboxing tal como la línea 11
        }
    }

    public static int sumEven(List<Integer> li) {
        int sum = 0;
        for (Integer i: li)
            if (i % 2 == 0)
                sum += i;
        return sum;
    }

    public static int sumEvenAtRuntime(List<Integer> li) {
        int sum = 0;
        for (Integer i : li)
            if (i.intValue() % 2 == 0)
                sum += i.intValue();
        return sum;
    }
}
