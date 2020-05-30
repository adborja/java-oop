package com.edu.cedesistemas.oop.model.vehicles;

public class SportElectricCar extends ElectricCar {
    public SportElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void power() {
        super.power();
        System.out.println("encender carro deportivo");
    }
}
