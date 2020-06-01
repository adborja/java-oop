package com.edu.cedesistemas.oop.model.vehicle;

public class Truck extends FuelCar {

    public Truck(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void power() {
        super.power();
        System.out.println("Encender Camión");
    }
}
