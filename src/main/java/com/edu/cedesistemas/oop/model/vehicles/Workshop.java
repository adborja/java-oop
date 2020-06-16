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

    public void testWorkshopElMecanico(){
        System.out.println();
        System.out.println("------------> Nombre Taller: " + name + " <------------");
        System.out.println();
        C carElectrico = (C) new ElectricCar(50, "Mazda", "Car Electric");
        repair(carElectrico);
        C carDiesel = (C) new DieselCar(30, "Ford", "Diesel Car");
        repair(carDiesel);
        C carFuel = (C) new FuelCar(30, "Toyota","Car Fuel");
        repair(carFuel);
        showStateWorksshop();
        deliver(carDiesel);
        showStateWorksshop();
    }

    public void testWorkshopTodoCarros() {
        System.out.println();
        System.out.println("------------> Nombre Taller: " + name + " <------------");
        System.out.println();
        C carElectrico = (C) new ElectricCar(50, "Mazda", "Car Electric");
        repair(carElectrico);
        C carDiesel = (C) new DieselCar(30, "Ford", "Diesel Car");
        repair(carDiesel);
        C carFuel = (C) new FuelCar(30, "Toyota", "Car Fuel");
        repair(carFuel);
        C carFuel2 = (C) new FuelCar(40, "Nissan", "Car Fuel");
        repair(carFuel2);
        showStateWorksshop();
        deliver(carDiesel);
        deliver(carElectrico);
        showStateWorksshop();
        C carDiesel2 = (C) new DieselCar(50, "International", "Diesel Car");
        repair(carDiesel2);
        showStateWorksshop();
    }

    public void testWorkshopOnlyElectricCar(){
        System.out.println();
        System.out.println("------------> Nombre Taller: " + name + " <------------");
        System.out.println();
        C carElectrico = (C) new ElectricCar(50, "Mazda", "Car Electric");
        repair(carElectrico);
        C carDiesel = (C) new DieselCar(30, "Ford", "Diesel Car");
        repair(carDiesel);
        C carFuel = (C) new FuelCar(30, "Toyota","Car Fuel");
        showStateWorksshop();
        deliver(carDiesel);
        deliver(carElectrico);
        showStateWorksshop();
    }

    public void showStateWorksshop(){
        System.out.println();
        System.out.println(">>> Nro de Carros en REPARACIÓN: " + listaCarrosReparacion.size());
        System.out.println(">>> Nro de Carros ENTREGADOS: " + listaCarrosEntregados.size());
        System.out.println();
    }

    public static void main(String[] args) {
        List<Car> r1 = new ArrayList<>();
        List<Car> e1 = new ArrayList<>();
        Workshop workshop1 = new Workshop("El Buen Mecanico", r1, e1);
        workshop1.testWorkshopElMecanico();

        List<Car> r2 = new ArrayList<>();
        List<Car> e2 = new ArrayList<>();
        Workshop workshop2 = new Workshop("Todo Carros", r2, e2);
        workshop2.testWorkshopTodoCarros();

        List<ElectricCar> r3 = new ArrayList<>();
        List<ElectricCar> e3 = new ArrayList<>();
        Workshop workshop3 = new Workshop("Electric Car Taller", r3, e3);
        workshop3.testWorkshopOnlyElectricCar();
    }
}
