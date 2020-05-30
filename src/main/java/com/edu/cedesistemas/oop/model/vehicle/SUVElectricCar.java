package com.edu.cedesistemas.oop.model.vehicle;

public class SUVElectricCar extends ElectricCar{
    public SUVElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void power() {
        super.power();
        System.out.println("Encender SUV Electric car");
    }
}
