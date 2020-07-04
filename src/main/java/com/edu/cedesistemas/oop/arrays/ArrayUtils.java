package com.edu.cedesistemas.oop.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ArrayUtils {
    private ArrayUtils() {}

    // Lesson 2 - - arrays
    public static void bubbleSort(Integer[] array) {
        boolean swapped = true;
        Integer j = 0;
        Integer tmp = 0;
        int n = array.length;

        while (swapped == true) {
            swapped = false;
            j++;
            for (int i = 0; i < n - 1; i++) {
                    if (array[i] > array[i + 1]) {
                        tmp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = tmp;
                        swapped = true;
                    }
                }
            }
        }


    public static void mergeSort(Integer[] array, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Integer[] l = new Integer[mid];
        Integer[] r = new Integer[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = array[i];
        }

        for (int i = mid; i < n; i++) {
            r[i - mid] = array[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(array, l, r, mid, n - mid);
    }

    private static void merge(Integer[] a, Integer[] l, Integer[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    // Lesson 2 -- ArrayList
    public static Integer sum(List<Integer> list) {
        // Implemente codigo
        Integer sum = 0;
        int n = list.size();
        for(int i=0;i<n;i++){
            sum = sum + list.get(i);
        }
        return sum;
    }

    /*
    public static List<Integer> primeNumbersTill(int n) {

        return IntStream.rangeClosed(2, n)

                .filter(x -> isPrime(x)).boxed()

                .collect(Collectors.toList());

    }

    private static boolean isPrime(int number) {

        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))

                .filter(n -> (n & 0X1) != 0)

                .allMatch(n -> number % n != 0);

    }
*/

    // Lesson 2 -- ArrayList
    public static List<Integer> getPrimeNumbers(int limit) {
        // Implemente codigo
        ArrayList<Integer> listaresultado = new ArrayList<>();
        int i = 0;
        int num = 0;
        int count = 0;
        for(i=1;i<=limit;i++){
            count = 0;
            for(num = i;num>=1;num--) {
                if (i % num == 0) {
                    count++;
                }
            }
            if(count == 2){
                listaresultado.add(i);
            }
        }

        return listaresultado;
    }

    // Lesson 2 - arrays
    public static int max(Integer[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = max;
            for (int j = i+1; j<array.length; j++) {
                if (array[j] > max) {
                    max = array[j];
                }
            }
        }
        return max;
    }

    // Lesson 2 -- ArrayList
    public static boolean contains(String element, List<String> list) {
        for (String e : list) {
            if (e.equals(element)) {
                return true;
            }
        }
        return false;
    }

    // Lesson 2 -- ArrayList
    public static void join(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : l2) {
            l1.add(i);
        }
    }

    // Lesson 2 -- ArrayList
    public static List<Integer> getOddNumbers(int limit) {
        List<Integer> odds = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            if (i % 2 != 0) {
                odds.add(i);
            }
        }
        return odds;
    }
}