package model.vehicle;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.util.List;
import java.time.LocalDateTime;

public abstract class Car implements Vehicle{

    protected String id;
    protected double traveledKms;
    protected String name;
    protected String type;
    protected double speed;
    private List<Segment> movements;
    private LocalDateTime lastMaintenanceDate;

    public  Car(double speed, String name, String type){
        this.speed = speed;
        this.name = name;
        this.type = type;
    }
    @Override
    public double move(Point a, Point B) {
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
    public LocalDateTime getlastMaintenanceDate() {
        return this.lastMaintenanceDate;
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
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public abstract void  thank();
}
