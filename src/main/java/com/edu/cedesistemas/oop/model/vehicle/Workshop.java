package com.edu.cedesistemas.oop.model.vehicle;

import java.time.LocalDateTime;
import java.util.List;

public class Workshop <T extends ElectricCar, Y extends ElectricCar> {
    private String  nombre;
    private List<ElectricCar> carEnReparacion;
    private List<ElectricCar> carEntregados;

    public Workshop(String nombre, List<T> carEnReparacion, List<Y> carEntregados) {
        this.nombre = nombre;
        this.carEnReparacion = (List<ElectricCar>) carEnReparacion;
        this.carEntregados = (List<ElectricCar>) carEntregados;
    }

    public <T extends ElectricCar> void repair(T car ){
        carEnReparacion.add(car);
        System.out.println("El carro se encuentra en reparacion");
    }

    public <T extends ElectricCar> void deliver(T car){
        carEntregados.add(car);
        carEnReparacion.remove(car);
        car.setLastMaintenanceDate(LocalDateTime.now());
        System.out.println("\n***Carro Entregado ");
    }

    public void carEnReparacion(){
        System.out.println("\n--------  Carros en reparacion  ----------");
        for(Car c:carEnReparacion)
            System.out.println("\n" + c.getName());
    }

    public void carEntregados(){
        System.out.println("\n--------  Carros Entregados ------------");
        for(Car c:carEntregados)
            System.out.println("\n" + c.getName());
    }

}
