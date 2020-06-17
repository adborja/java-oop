package com.edu.cedesistemas.oop.model.vehiculo;

public class DieselCar extends Car{
    public DieselCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("tanqueando carro diesel");
    }

    @Override
    public boolean getLastMaintenanceDate() {
        return false;
    }

    @Override
    public void power() {
    System.out.println("encendiendo auto diesel");
    }

}
