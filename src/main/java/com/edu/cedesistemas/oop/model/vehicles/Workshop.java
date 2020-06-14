package com.edu.cedesistemas.oop.model.vehicles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Workshop{
    private final String name;
    List<Car> listaCarrosReparacion = new ArrayList<>();
    List<Car> listaCarrosEntregados = new ArrayList<>();

    public Workshop(String name){
        this.name = name;
    }

    public void repair(Car car){
        listaCarrosReparacion.add(car);
        System.out.println("Carro " + car.getName() + " Agregado a lista de carros en Reparación");
    }

    public void deliver(Car car){
        listaCarrosReparacion.remove(car);
        System.out.println("Carro " + car.getName() + " Retirado de la lista de carros en Reparación");
        car.setLastMaintenanceDate(LocalDateTime.now());
        listaCarrosEntregados.add(car);
        System.out.println("Carro " + car.getName() + " Agregado a lista de carros Entregados");
    }

    public void Testworkshop(){
        Car carElectrico = new ElectricCar(50, "Mazda", "Car Electric");
        repair(carElectrico);
        Car carDiesel = new DieselCar(30, "Ford", "Diesel Car");
        repair(carDiesel);
        Car carFuel = new FuelCar(30, "Toyota","Car Fuel");
        repair(carFuel);
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
        Workshop workshop = new Workshop("Taller 1");
        workshop.Testworkshop();
    }
}
