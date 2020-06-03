package com.edu.cedesistemas.oop.model.vehiculo;

public class SportElectricCar extends ElectricCar{
    public SportElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void power() {
    System.out.println("Encendiendo carro deportivo");
    }
}
