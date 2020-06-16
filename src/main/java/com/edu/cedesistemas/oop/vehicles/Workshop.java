package com.edu.cedesistemas.oop.vehicles;

import com.edu.cedesistemas.oop.model.geometry.Circle;

import java.time.LocalDateTime;
import java.util.List;

public class Workshop<T extends ElictricCar,Y extends ElictricCar> {
    private String  nombre;
    private List<ElictricCar> carrosReparacion;
    private List<ElictricCar> carrosEntregados;

    public Workshop(String nombre, List<T> carrosReparacion, List<Y> carrosEntregados) {
        this.nombre = nombre;
        this.carrosReparacion = (List<ElictricCar>) carrosReparacion;
        this.carrosEntregados = (List<ElictricCar>) carrosEntregados;
    }

    public <T extends ElictricCar> void repair(T carElectric){
        carrosReparacion.add(carElectric);
        System.out.println("el carro se encuentra en reparacion");
    }

    public <T extends ElictricCar> void deliver(T carElectric){
        carrosEntregados.add(carElectric);
        carrosReparacion.remove(carElectric);
        carElectric.setLastMaintenanceDate(LocalDateTime.now());
        System.out.println("el carro fue reparado");
    }

    public void carrosReparacion(){
        System.out.println("\nCarros en repacion *********");
        for(ElictricCar c:carrosReparacion)
            System.out.println("\n" + c.getName());
    }

    public void carrosEntregados(){
        System.out.println("\nCarros Entregados **********");
        for(ElictricCar c:carrosEntregados)
            System.out.println("\n" + c.getName());
    }

}
