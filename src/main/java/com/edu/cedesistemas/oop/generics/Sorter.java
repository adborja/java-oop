package com.edu.cedesistemas.oop.generics;

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
    //Método de ordenamiento principal
    public static <T extends Comparable<? super T>>void mergeSort(T[] array, int first, int last)
    {
        if (first < last)
        {
            int middle = (first + last) / 2; //busca el punto medio
            mergeSort(array, first, middle); // ordena la primera mitad
            mergeSort(array, middle + 1, last);  //ordena la segunda mitad
            merge(array, first, middle, last); //fisionar las mitades ordenadas
        }
    }

    // Unir dos matrices de array[].
    public static <T extends Comparable<? super T>>void merge(T[] array, int first, int middle, int last)
    {
        T[] leftArray  = (T[]) new Comparable[middle - first + 1];
        T[] rightArray = (T[]) new Comparable[last - middle];

        // Llenar matriz izquierda
        for (int i = 0; i < leftArray.length; ++i)
            leftArray[i] = array[first + i];

        // Llenar matriz derecha
        for (int i = 0; i < rightArray.length; ++i)
            rightArray[i] = array[middle + 1 + i];

        /* Unir matrices temporales */
        // índices iniciales de primera y segunda submatrices
        int leftIndex = 0, rightIndex = 0;

        //el índice al que vamos a agregar primero las submatrices a la matriz principal
        int currentIndex = first;

        //compare cada índice de las submatrices agregando el valor más bajo al índice actual
        while (leftIndex < leftArray.length && rightIndex < rightArray.length)
        {
            if (leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0) {
                array[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            }
            else {
                array[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        // copie los elementos restantes de leftArray [] si los hay
        while (leftIndex < leftArray.length) array[currentIndex++] = leftArray[leftIndex++];

        //copie los elementos restantes de rightArray [] si los hay
        while (rightIndex < rightArray.length) array[currentIndex++] = rightArray[rightIndex++];
    }
}
