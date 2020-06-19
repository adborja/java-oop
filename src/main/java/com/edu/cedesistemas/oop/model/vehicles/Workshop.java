package com.edu.cedesistemas.oop.model.vehicles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Workshop<E extends ElectricCar> {
    private final String name;
    List<? extends ElectricCar> listaCarrosReparacion = new ArrayList<>();
    List<? extends ElectricCar> listaCarrosEntregados = new ArrayList<>();

    public Workshop(String name, List<? extends E> r, List<? extends E> e){
        this.name = name;
        this.listaCarrosReparacion = r;
        this.listaCarrosEntregados = e;
    }

    public void repair(List<? extends ElectricCar> list){
        this.listaCarrosReparacion = list;
        System.out.println("Carro: >>> Agregado a lista de carros en Reparación");
    }

    public void deliver(List<? extends ElectricCar> list, String name){
        for(ElectricCar e : this.listaCarrosReparacion){
            if (e.getName().equals(name)){
                this.listaCarrosReparacion.remove(e);
                e.setLastMaintenanceDate(LocalDateTime.now());
                System.out.println("Carro: >>> Retirado de la lista de carros en Reparación");
                this.listaCarrosEntregados = list;
                System.out.println("Carro: >>> Agregado a lista de carros Entregados");
            }
        }
    }


    public void testWorkshopElMecanico(){
        System.out.println();
        System.out.println("------------> Nombre Taller: " + name + " <------------");
        System.out.println();
        List<ElectricCar> carsRep = new ArrayList<>();
        List<ElectricCar> carsEnt = new ArrayList<>();

        ElectricCar carElectrico = new ElectricCar(50, "Mazda1", "Car Electric");
        carsRep.add(carElectrico);
        repair(carsRep);
        showStateWorksshop();

        ElectricCar carElectrico2 = new ElectricCar(60, "Mazda2", "Car Electric");
        carsRep.add(carElectrico2);
        repair(carsRep);
        showStateWorksshop();

        /**DieselCar dieselCar = new DieselCar(30, "Ford", "Diesel Car");
        carsRep.add(dieselCar);
        repair(carsRep);**/

        carsEnt.add(carElectrico);
        deliver(carsEnt, carElectrico.getName());
        showStateWorksshop();

        ElectricCar carElectrico3 = new ElectricCar(65, "Toyota", "Car Electric");
        carsRep.add(carElectrico3);
        repair(carsRep);
        showStateWorksshop();

        /**FuelCar fuelCar = new FuelCar(30, "Ford", "Fuel Car");
        carsRep.add(fuelCar);
        repair(carsRep);**/

        ElectricCar carElectrico4 = new ElectricCar(70, "Nissan", "Car Electric");
        carsRep.add(carElectrico4);
        repair(carsRep);
        showStateWorksshop();

        carsEnt.add(carElectrico3);
        deliver(carsEnt, carElectrico3.getName());
        showStateWorksshop();
    }

    public void showStateWorksshop(){
        System.out.println();
        System.out.println(">>> Nro de Carros en REPARACIÓN: " + listaCarrosReparacion.size());
        System.out.println(">>> Nro de Carros ENTREGADOS: " + listaCarrosEntregados.size());
        System.out.println();
    }

    public static void main(String[] args) {
        List<ElectricCar> r1 = new ArrayList<>();
        List<ElectricCar> e1 = new ArrayList<>();
        Workshop workshop1 = new Workshop("El Buen Mecanico", r1, e1);
        workshop1.testWorkshopElMecanico();

        /*List<Car> r2 = new ArrayList<>();
        List<Car> e2 = new ArrayList<>();
        Workshop workshop2 = new Workshop("Todo Carros", r2, e2);
        workshop2.testWorkshopTodoCarros();

        List<ElectricCar> r3 = new ArrayList<>();
        List<ElectricCar> e3 = new ArrayList<>();
        Workshop workshop3 = new Workshop("Electric Car Taller", r3, e3);
        workshop3.testWorkshopOnlyElectricCar();**/
    }

}
