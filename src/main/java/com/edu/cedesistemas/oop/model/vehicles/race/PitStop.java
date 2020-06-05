package com.edu.cedesistemas.oop.model.vehicles.race;

public class PitStop<T extends RaceCar> {
    private final T car;

    public PitStop(T car) {
        this.car = car;
    }

    public T getCar() {
        return car;
    }

    public RaceCar.Team getTeam() {
        return car.getTeam();
    }

    public void changeTires() {
        System.out.println("changing tires for " + car.getName());
    }

    public void tank(int quantity) {
        car.tank(quantity);
    }

    public void repair() {
        System.out.println("repairing car " + car.getName());
    }

    public void adjust() {
        System.out.println("adjusting car " + car.getName());
    }
}