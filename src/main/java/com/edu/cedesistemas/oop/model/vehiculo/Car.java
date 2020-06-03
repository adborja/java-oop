package com.edu.cedesistemas.oop.model.vehiculo;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Car implements Vehicle {

    protected String id;
    protected double traveledkms;
    protected String name;
    protected String type;
    protected double speed;
    private List<Segment> movements;
    private LocalDateTime lastMainteinceDate;

    public Car(double speed, String name, String type){
        this.speed=speed;
        this.name=name;
        this.type=type;
    }
    public abstract void tank();

    @Override
    public double move(Point x, Point y) {
        return 0;
    }

    @Override
    public List<Segment> getMovements() {
        return this.movements;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void setLastMainteinceDate(LocalDateTime date) {
        this.lastMainteinceDate=date;
    }

    @Override
    public LocalDateTime setLastMainteinceDate() {
        return this.lastMainteinceDate;
    }

    @Override
    public double getTraveledkms() {
        return this.traveledkms;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

    public abstract boolean getLastMaintenanceDate();
}
