package com.edu.cedesistemas.oop.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Taller Lección 4 – Collections, ejercicio 3
//Based on https://dzone.com/articles/hashmap-%E2%80%93-single-key-and
public class SapanglishMap {

    public static void main(String[] args) {
        Map<Integer, List<String>> numeros = new HashMap<>();

        List<String> uno = new ArrayList<>();
        uno.add("Uno");
        uno.add("One");

        List<String> dos = new ArrayList<>();
        dos.add("dos");
        dos.add("two");

        List<String> tres = new ArrayList<>();
        tres.add("tres");
        tres.add("three");

        List<String> cuatro = new ArrayList<>();
        tres.add("cuatro");
        tres.add("four");

        List<String> cinco = new ArrayList<>();
        tres.add("cinco");
        tres.add("five");

        numeros.put(1, uno);
        numeros.put(2, dos);
        numeros.put(3, tres);
        numeros.put(4, cuatro);
        numeros.put(5, cinco);

        for (Map.Entry<Integer, List<String>> entry : numeros.entrySet()) {
            Integer clave = entry.getKey();
            List<String> valores = entry.getValue();
            System.out.println("Key = " + clave);
            System.out.println("Values = " + valores);
        }
    }


}
