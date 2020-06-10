package com.edu.cedesistemas.oop.model.vehicles;
import com.edu.cedesistemas.oop.model.vehicles.DieselCar;

public class DieselCar extends Car{

    public DieselCar(double speed, String name, double consumption) {
        super(speed, name, consumption);
    }

    @Override
    public void tank(int quantity) {

    }

    @Override
    public void tank() {
        System.out.println("Tanquear carro diesel");
    }

    @Override
    public void power() {
        System.out.println("Prender carro diesel");
    }

}
