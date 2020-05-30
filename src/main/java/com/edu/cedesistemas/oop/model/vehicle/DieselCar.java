package com.edu.cedesistemas.oop.model.vehicle;

public class DieselCar extends Car {

    public DieselCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanquear Carro Diesel");

    }

    @Override
    public void power() {
        System.out.println("Encender Carro Diesel");

    }
}
