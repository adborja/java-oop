package com.edu.cedesistemas.oop.model.vehicles;

public class ElectricCar extends Car{
    public ElectricCar(double speed, String name, String type){
        super(speed, name, type);
    }

    public void tank(){
        System.out.println("Cargar carro electrico");
    }

    public void power(){
        System.out.println("Encendiendo carro electrico");
    }
}
