package com.edu.cedesistemas.oop.model.vehicles;

import com.edu.cedesistemas.oop.model.vehicles.ElectricCar;

public class ElectricCar extends Car {
    public ElectricCar(double speed, String name, double consumption) {
        super(speed, name, consumption);
    }

    @Override
    public void tank(int quantity) {

    }

    @Override
    public void tank() {

    }

    @Override
    public void power() {
        System.out.println("Encender Electric car");
    }
}