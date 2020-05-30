package com.edu.cedesistemas.oop.model.vehicles;

public class GasCar extends Car{
    public GasCar(double speed, String name, String type){
        super(speed, name, type);
    }

    public void tank(){
        System.out.println("Tanquear carro a gas");
    }

    public void power(){
        System.out.println("Encendiendo carro a gas");
    }
}
