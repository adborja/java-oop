package com.edu.cedesistemas.oop.model.vehicles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Workshop <C extends Car>{
    private final String name;
    List<C> listaCarrosReparacion = new ArrayList<>();
    List<C> listaCarrosEntregados = new ArrayList<>();

    public Workshop(String name, List<C> r, List<C> e){
        this.name = name;
        this.listaCarrosReparacion = r;
        this.listaCarrosEntregados = e;
    }

    public void repair(C car){
        this.listaCarrosReparacion.add(car);
        System.out.println("Carro: " + car.getName() + " >>> Agregado a lista de carros en Reparación");
    }

    public void deliver(C car){
        this.listaCarrosReparacion.remove(car);
        System.out.println("Carro: " + car.getName() + " >>> Retirado de la lista de carros en Reparación");
        car.setLastMaintenanceDate(LocalDateTime.now());
        this.listaCarrosEntregados.add(car);
        System.out.println("Carro: " + car.getName() + " >>> Agregado a lista de carros Entregados");
    }

    public void Testworkshop(){
        C carElectrico = (C) new ElectricCar(50, "Mazda", "Car Electric");
        repair(carElectrico);
        C carDiesel = (C) new DieselCar(30, "Ford", "Diesel Car");
        repair(carDiesel);
        C carFuel = (C) new FuelCar(30, "Toyota","Car Fuel");
        repair(carFuel);
        System.out.println();
        System.out.println("------------> Nombre Taller: " + this.name + " <------------");
        System.out.println();
        System.out.println(">>> Nro de Carros en REPARACIÓN: " + listaCarrosReparacion.size());
        System.out.println(">>> Nro de Carros ENTREGADOS: " + listaCarrosEntregados.size());
        System.out.println();
        deliver(carDiesel);
        System.out.println();
        System.out.println(">>> Nro de Carros en REPARACIÓN: " + listaCarrosReparacion.size());
        System.out.println(">>> Nro de Carros ENTREGADOS: " + listaCarrosEntregados.size());
        System.out.println();
    }

    public static void main(String[] args) {
        List<Car> r = new ArrayList<>();
        List<Car> e = new ArrayList<>();
        Workshop workshop = new Workshop("El Buen Mecanico", r, e);
        workshop.Testworkshop();

        List<Car> r2 = new ArrayList<>();
        List<Car> e2 = new ArrayList<>();
        Workshop workshop2 = new Workshop("Todo Carros", r2, e2);
        workshop2.Testworkshop();

    }
}
