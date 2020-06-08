package com.edu.cedesistemas.oop.model.vehicle.race;

import com.edu.cedesistemas.oop.model.vehicle.FuelCar;

public class RaceCar extends FuelCar {
    protected final Team team;

    public RaceCar(double speed, String name, double consumption, String type, Team team) {
        super(speed, name, consumption, type);
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public enum Team {
        FERRARI,
        MCLAREN,
        RED_BULL,
        MERCEDES,
        RENAULT
    }
}
