package com.edu.cedesistemas.oop.model.vehicles;

public class GasCar extends Car {

    public GasCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanquear carro de gas");
    }

    @Override
    public void power() {
        System.out.println("Prender carro de gas");
    }

}
