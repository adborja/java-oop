package com.edu.cedesistemas.oop.model.vehicle;

public class ElectricCar extends Car {
    public ElectricCar(double speed, String name, double consumption) {
        super(speed, name, consumption);
    }

    @Override
    public void tank(int quantity) {
        System.out.println("electric car charging");
        this.currentTankQuantity += quantity;
    }

    @Override
    public void power() {
        System.out.println("car powering up using batteries");
    }

}