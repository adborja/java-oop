package com.edu.cedesistemas.oop.model.vehicle;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ElectricCar electrico1 = new ElectricCar(20,"toyota",1);
        ElectricCar electrico2 = new ElectricCar(30,"renault",5);
        ElectricCar electrico3 = new ElectricCar(10,"mazda",9);
        DieselCar dieselCar = new DieselCar(10,"toyotaElec",0);
        GasCar gasCar = new GasCar(12, "Chevrolett", 20,"Carro a gas");

        List<ElectricCar> carEnReparacion = new ArrayList();
        List<ElectricCar> carEntregados = new ArrayList();

        Workshop workshop = new Workshop("Repaciones",carEnReparacion,carEntregados);
        workshop.repair(electrico1);
        workshop.deliver(electrico2);
        workshop.repair(electrico3);
        workshop.repair(electrico2);
        workshop.deliver(electrico1);
        workshop.deliver(electrico2);

       /* //Sólo permite reparar carros electricos,estos fallan
        workshop.repair(dieselCar);
        workshop.repair(gasCar);*/

        workshop.carEnReparacion();
        workshop.carEntregados();
    }
}
