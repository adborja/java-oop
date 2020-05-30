package com.edu.cedesistemas.oop.model.vehicles;

public class ElectricCar extends Car {
    public ElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanqueando Carro Electrico");
    }

    @Override
    public void power() {
        System.out.println("Prendiendo Carro Electrico");
    }
}
