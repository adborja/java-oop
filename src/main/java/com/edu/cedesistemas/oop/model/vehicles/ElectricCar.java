package com.edu.cedesistemas.oop.model.vehicles;

import java.awt.*;

public class ElectricCar extends Car {

    public ElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("tanquear carro electrico");

    }

    @Override
    public double move(Point p1, Point p2) {
        return 0;
    }

    @Override
    public void power() {
        System.out.println("encender carro electrico");

    }
}
