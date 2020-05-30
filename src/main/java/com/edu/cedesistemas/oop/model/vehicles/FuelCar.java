package com.edu.cedesistemas.oop.model.vehicles;

public class FuelCar extends Car {
    public FuelCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanquear carro FuelCar");
    }

    @Override
    public void power() {
        System.out.println("Encender carro FuelCar");
    }
}