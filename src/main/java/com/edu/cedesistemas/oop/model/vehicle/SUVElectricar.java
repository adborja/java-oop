package com.edu.cedesistemas.oop.model.vehicle;

public class SUVElectricar extends ElectricCar {
    public SUVElectricar(double speed, String name, String type) {
        super(speed, name, type);
    }

    //Polimorfismo
    @Override
    public void power() {
        super.power();
        System.out.println("Prendiendo carro Electrico SUV");
    }
}
