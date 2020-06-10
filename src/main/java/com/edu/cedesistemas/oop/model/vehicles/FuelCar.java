package com.edu.cedesistemas.oop.model.vehicles;

public class FuelCar extends Car{

    public FuelCar(double speed, String name, double consumption) {
        super(speed, name, consumption);
    }

    @Override
    public void tank(int quantity) {

    }

    @Override
    public void tank() {
        System.out.println("Tanquear carro fuel");
    }

    @Override
    public void power() {
        System.out.println("Prender carro fuel");
    }

}
