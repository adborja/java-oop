package com.edu.cedesistemas.oop.model.vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkShop {

    private String name;
    private List<Car> repairedCars = new ArrayList<>();
    private List<Car> deliveredCars = new ArrayList<>();

    public void repair(Car car){
        repairedCars.add(car);
        System.out.println("The car has been repaired");
    }

    public void deliver(Car car){
        repairedCars.remove(car);
        car.setLastMaintenanceDate(LocalDateTime.now());
        deliveredCars.add(car);
        System.out.println("The car has been delivered");
    }



}
