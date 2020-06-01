package com.edu.cedesistemas.oop.model.vehicle;

public class ElectricCar extends Car {


    public ElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanquear carro electrico");
    }

    @Override
    public void power() {
        System.out.println("Arrancar carro electrico");

    }
}
