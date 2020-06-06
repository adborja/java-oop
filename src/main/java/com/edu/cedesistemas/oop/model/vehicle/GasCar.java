package com.edu.cedesistemas.oop.model.vehicle;

public class GasCar extends Car {

    public GasCar(double speed, String name,  double cons, String type) {
        super(speed, name, cons);
    }


    @Override
    public void power() {
        System.out.println("Arrancar carro de gas");
    }

    @Override
    public void tank(int quantity) {
        System.out.println("Tanquear carro de gas");
    }
}
