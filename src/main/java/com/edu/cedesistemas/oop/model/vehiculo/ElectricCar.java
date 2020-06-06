package com.edu.cedesistemas.oop.model.vehiculo;

public class ElectricCar extends Car{
    public ElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("tanquear carro electrico");

    }

    @Override
    public boolean getLastMaintenanceDate() {
        return false;
    }

    @Override
    public void power() {
        System.out.println("encender carro electrico");
    }
}
