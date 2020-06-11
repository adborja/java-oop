package com.edu.cedesistemas.oop.vehicles;

public class GasCar extends Car{
    public GasCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tanck() {
        System.out.println("tanquear carro a Gas");
    }

    @Override
    public void power() {
        System.out.println("encender carro a Gas");
    }
}
