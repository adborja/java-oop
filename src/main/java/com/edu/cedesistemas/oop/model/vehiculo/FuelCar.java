package com.edu.cedesistemas.oop.model.vehiculo;

public class FuelCar extends Car{
    public FuelCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
    System.out.println("tanquear fuelcar");
    }

    @Override
    public boolean getLastMaintenanceDate() {
        return false;
    }

    @Override
    public void power() {
    System.out.println("Encender fuelcar");
    }
}
