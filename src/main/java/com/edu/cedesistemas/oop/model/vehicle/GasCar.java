package com.edu.cedesistemas.oop.model.vehicle;

public class GasCar extends Car {

    public GasCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanquear Carro a Gas");
    }

    @Override
    public void power() {
        System.out.println("Encender Carro a Gas");

    }
}
