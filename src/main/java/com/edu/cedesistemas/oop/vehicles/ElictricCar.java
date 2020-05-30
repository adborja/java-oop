package com.edu.cedesistemas.oop.vehicles;

public class ElictricCar extends Car {

    public ElictricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tanck() {
        System.out.println("taquear carro electrico");
    }

    @Override
    public void power() {
        System.out.println("encender carro electrico");
    }
}
