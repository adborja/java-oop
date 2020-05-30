package com.edu.cedesistemas.oop.model.vehicles;

import static java.lang.System.*;

public class SportElectricCar extends ElectricCar{
    public SportElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    //polimorfismo sobreescribiendo metodo power
    public void power(){
        out.println("Encender carro electrico");
    }

}
