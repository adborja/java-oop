package com.edu.cedesistemas.oop.tareas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TareaMapas {

    public void tareaMapas(List<Employee> listEmpleyee){
        Map<Employee,String> m = new HashMap<>();


        for(Employee a : listEmpleyee){
            m.put(a,a.getNombre());
        }

        for (Map.Entry<Employee,String> e : m.entrySet())
            System.out.println("\n" + e.getKey().getID() + ": " + e.getValue() + " ******");

        m.forEach((x, y)->{
          System.out.println("\n" + x.getID()+ " - "+ y);
        });

    }

    public void numerosEnteros (Map<String,String> a, int numero) {
        int i = 0;

        for (Map.Entry<String,String> e : a.entrySet()) {

            if (i == (numero - 1)){
                System.out.println("\n****** " + e.getKey() + " " + e.getValue() + " ******");
            }
            i++;

        }
    }

}
