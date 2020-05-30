package com.edu.cedesistemas.oop.model.vehicle;
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
    private LocalDateTime lastMaintenanceDate;
    public Car(double speed, String name, String type) {
        this.speed = speed;
        this.name = name;
        this.type = type;
    }
    public double move(Point a, Point b) {
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
    public void setLastMaintenanceDate(LocalDateTime dateTime) {
        lastMaintenanceDate = dateTime;
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
    public abstract void tank();
}
