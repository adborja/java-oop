package com.edu.cedesistemas.oop.vehicles;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Car implements Vehicle{
    protected String id;
    protected double traveledKms;
    protected double speed;
    protected String name;
    protected String type;
    List<Segment> movements;
    LocalDateTime lastMaintenanceDate;

    public Car(double speed, String name,String type) {
        this.speed = speed;
        this.name = name;
        this.type = type;
    }

    @Override
    public double move(Point x, Point y) {
        return 0;
    }

    @Override
    public List<Segment> getMovements() {
        return movements;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setLastMaintenanceDate(LocalDateTime lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    @Override
    public LocalDateTime getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    @Override
    public double getTraveledKms() {
        return traveledKms;
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
        this.id = id;
    }

    public abstract void tanck();
}
