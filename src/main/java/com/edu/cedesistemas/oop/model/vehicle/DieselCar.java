package com.edu.cedesistemas.oop.model.vehicle;

public class DieselCar extends Car {
    public DieselCar(double speed, String name, double consumption) {
        super(speed, name, consumption);
    }

    @Override
    public void tank(int quantity) {
        System.out.println("diesel car tanking");
        this.currentTankQuantity += quantity;
    }

    @Override
    public void power() {
        System.out.println("car powering up using diesel");
    }
}