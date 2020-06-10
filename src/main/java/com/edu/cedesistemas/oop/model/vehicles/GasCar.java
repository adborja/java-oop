package com.edu.cedesistemas.oop.model.vehicles;

public class GasCar extends Car {

    public GasCar(double speed, String name, double consumption) {
        super(speed, name, consumption);
    }

    @Override
    public void tank(int quantity) {

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
