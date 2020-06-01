package com.edu.cedesistemas.oop.model.vehicle;

// Lesson 2 -- Inner classes
public class GasCar extends Car {
    public GasCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("gas car tanking");
    }

    @Override
    public void power() {
        System.out.println("car powering up using gas");
    }
}