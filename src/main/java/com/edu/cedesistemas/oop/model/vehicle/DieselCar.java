package com.edu.cedesistemas.oop.model.vehicle;

public class DieselCar extends Car {
    public DieselCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("diesel car tanking");
    }

    @Override
    public void power() {
        System.out.println("car powering up using diesel");
    }
}
