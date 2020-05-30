package com.edu.cedesistemas.oop.model.vehicle;

public class FuelCar extends Car{
    public FuelCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanqueando Feul car");
    }

    @Override
    public void power() {
        System.out.println("Tanqueando Diesel car");
    }
}
