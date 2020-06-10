package com.edu.cedesistemas.oop.model.vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Workshop {
    private String name;
    private List<Car> reparationCars = new ArrayList<>();
    private List<Car> deliveredCars = new ArrayList<>();

    public void repair(Car car){
        reparationCars.add(car);
        System.out.println(("The car is in reparation"));
    }

    public void deliver(Car car){
        reparationCars.remove(car);
        deliveredCars.add(car);
        System.out.println("The car was delivered");
        car.setLastMaintenanceDate(LocalDateTime.now());
    }


}
