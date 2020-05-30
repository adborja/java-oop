package com.edu.cedesistemas.oop.vehicles;

public class DieselCar extends Car {


    public DieselCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tanck() {
        System.out.println("taquear carro diesel");
    }

    @Override
    public void power() {
        System.out.println("encender carro diesel");

    }
}
