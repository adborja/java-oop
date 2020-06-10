package com.edu.cedesistemas.oop.model.vehicle;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int i = 0;
        List<Car> listcar = new ArrayList<>();
        Car electric = new ElectricCar(50, "prueba1", 40);
        Car diesel = new DieselCar(30, "prueba2", 50 );
        Car fuel = new FuelCar(60, "prueba3", 70, "mecanico");
        Car truck = new Truck(80, "NQR", 100, "mediano");

        listcar.add(electric);
        listcar.add(diesel);
        listcar.add(fuel);
        listcar.add(truck);
        for(Car car : listcar){
            if(car instanceof ElectricCar){
                Workshop workshop = new Workshop();
                workshop.repair(car);
                workshop.deliver(car);
        }else{
                System.out.println("It is not possible to repair the car");
            }
        }


    }

}