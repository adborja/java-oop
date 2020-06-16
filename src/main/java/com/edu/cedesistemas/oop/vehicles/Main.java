package com.edu.cedesistemas.oop.vehicles;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        ElictricCar electricar1 = new ElictricCar(20,"renault","electrico");
        ElictricCar electricar2 = new ElictricCar(30,"renault1","electrico");
        ElictricCar electricar3 = new ElictricCar(10,"renault2","electrico");
        FuelCar fuelCar = new FuelCar(10,"toyotaElec","electrico");

        List<ElictricCar> listaReparacion = new ArrayList();

        List<ElictricCar> listaDeliver = new ArrayList();


        Workshop workshop = new Workshop("Repaciones",listaReparacion,listaDeliver);
        workshop.repair(electricar1);
        workshop.deliver(electricar1);
        workshop.repair(electricar2);
        workshop.repair(electricar3);
        //workshop.repair(fuelCar); // valida efectivamente que es un car electrico

    }
}
