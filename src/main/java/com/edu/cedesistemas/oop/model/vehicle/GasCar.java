package com.edu.cedesistemas.oop.model.vehicle;

public class GasCar extends FuelCar {
    public GasCar(double speed, String name, double consumption, String type) {
        super(speed, name, consumption, type);
    }

    @Override
    public void tank(int quantity) {
        System.out.println("gas car tanking");
        this.currentTankQuantity += quantity;
    }

    @Override
    public void power() {
        System.out.println("car powering up using gas");
    }
}