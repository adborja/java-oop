package com.edu.cedesistemas.oop.model.vehiculo;

public class Truck extends FuelCar{
    public Truck(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void power() {
        System.out.println("");
    }
}
