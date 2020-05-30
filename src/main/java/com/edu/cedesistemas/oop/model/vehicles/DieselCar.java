package com.edu.cedesistemas.oop.model.vehicles;

public class DieselCar extends Car{
    public DieselCar(double speed, String name, String type){
        super(speed, name, type);
    }

    public void tank(){
        System.out.println("Tanquear carro diesel");
    }

    public void power(){
        System.out.println("Encendiendo carro diesel");
    }
}
