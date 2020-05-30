package com.edu.cedesistemas.oop.model.vehicles;

public class FuelCar extends Car{
    public FuelCar(double speed, String name, String type){
        super(speed, name, type);
    }

    public void tank(){
        System.out.println("Tanquear carro a gasolina");
    }

    public void power(){
        System.out.println("Encendiendo carro a gasolina");
    }
}
