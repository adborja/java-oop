package com.edu.cedesistemas.oop.model.vehicle;

public class FuelCar  extends Car {
    public FuelCar(double speed, String name, double consumption, String type) {
        super(speed, name, consumption);
    }



    @Override
    public void power() {
        System.out.println("Arrancar carro de combustible");

    }

    @Override
    public void tank(int quantity) {
        System.out.println("Tanquear carro de combustible");
    }
}
