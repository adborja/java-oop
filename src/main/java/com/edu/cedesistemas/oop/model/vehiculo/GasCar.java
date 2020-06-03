package com.edu.cedesistemas.oop.model.vehiculo;

public class GasCar extends Car{
    public GasCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
    System.out.println("tanquear carro gas");
    }

    @Override
    public void power() {
    System.out.println("encendiendo carro gas");
    }
}
