package com.edu.cedesistemas.oop.model.vehicle;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ElectricCar electricCar = new ElectricCar(20,"Tesla",20);
       // DieselCar dieselCar = new DieselCar(10,"Toyota",50);
        //FuelCar fuelCar = new FuelCar(80,"Aveo",80, "Sport");
        //Truck truck = new Truck(120,"Mack",500,"Anthem");

        List<Car> carList= new ArrayList();

        carList.add(electricCar);
        //carList.add(dieselCar);
       // carList.add(fuelCar);
       // carList.add(truck);

        for(Car car: carList){

            if(car instanceof ElectricCar ){
                WorkShop workShop = new WorkShop();
                workShop.deliver(car);
                workShop.repair(car);
            }
            else{
                throw new RuntimeException();
            }

        }
    }

}
