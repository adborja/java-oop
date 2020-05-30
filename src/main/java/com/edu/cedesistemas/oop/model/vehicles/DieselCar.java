package com.edu.cedesistemas.oop.model.vehicles;

public class DieselCar extends Car {
    public DieselCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanquear carro Diesel");
    }

    @Override
    public void power() {
        System.out.println("Encender carro Diesel");
    }
}
