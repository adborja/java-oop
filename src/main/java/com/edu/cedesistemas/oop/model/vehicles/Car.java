package com.edu.cedesistemas.oop.model.vehicles;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Car implements Vehicle{
    protected String id;
    protected double traveledKms;
    protected String name;
    protected String type;
    protected double speed;
    private List<Segment> movements;
    private LocalDateTime lastMantenanceDate;

    public Car(double speed, String name, String type){
        this.speed = speed;
        this.name = name;
        this.type = type;
    }

    public double move(Point a, Point b){
        return 0;
    }

    public List<Segment> getMovements() {
        return movements;
    }

    public double getSpeed() {
        return 0;
    }

    public void setLastMaintenanceDate(LocalDateTime dateTime) {
        lastMantenanceDate = dateTime;
    }

    public LocalDateTime getLastMaintenanceDate() {
        return lastMantenanceDate;
    }

    public double getTraveledKms() {
        return traveledKms;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public abstract void tank();
}
