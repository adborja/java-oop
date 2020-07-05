package com.edu.cedesistemas.oop.model.vehicle;

import java.time.LocalDateTime;
import java.util.List;

public class WorkShop<X extends ElectricCar,Y extends ElectricCar> {

        private String name;
        private List<ElectricCar> repairCars;
        private List<ElectricCar> deliveredCars;


    public <T extends ElectricCar> void repairCar( T eCar){
        repairCars.add(eCar);
    }

    public <T extends ElectricCar> void deliveredCar(T eCar){
        deliveredCars.add(eCar);
        repairCars.remove(eCar);
        eCar.setLastMaintenanceDate(LocalDateTime.now());

    }


    }




