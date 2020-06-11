package com.edu.cedesistemas.oop.vehicles;

public class SportElectricCar extends ElictricCar{
    public SportElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void power() {
        super.power();
        System.out.println("encender carro electrico deportivo");
    }
}
