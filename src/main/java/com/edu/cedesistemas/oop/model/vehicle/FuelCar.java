package com.edu.cedesistemas.oop.model.vehicle;

public class FuelCar extends Car {

    public FuelCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanquear carro con gasolina");
    }

    @Override
    public void power() {
        System.out.println("Encender carro a gasolina");
    }
}
