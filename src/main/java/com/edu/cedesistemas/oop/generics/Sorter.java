package com.edu.cedesistemas.oop.generics;

import java.util.ArrayList;
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
    public static <T extends Comparable<T>>void mergeSort(List<T> A, int izq, int der){
        if (izq < der){
            int m=(izq+der)/2;
            mergeSort(A,izq, m);
            mergeSort(A,m+1, der);
            merge(A,izq, m, der);
        }
    }

    public static <T extends Comparable<T>>void merge(List<T> A,int izq, int m, int der){
        int i, j, k;
        List<T> B = new ArrayList<>(A.size());
        //int [] B = new int[A.length]; //array auxiliar

        for (i=izq; i<=der; i++)      //copia ambas mitades en el array auxiliar
            // B[i]=A[i];
            B.add(A.get(i));

        i=izq; j=m+1; k=izq;

/*        while (i<=m && j<=der) //copia el siguiente elemento más grande
            // if (B[i]<=B[j])
            if(B.get(i). B.get(j))
                A[k++]=B[i++];
            else
                A[k++]=B[j++];

        while (i<=m)         //copia los elementos que quedan de la
            A[k++]=B[i++]; //primera mitad (si los hay)*/
}
