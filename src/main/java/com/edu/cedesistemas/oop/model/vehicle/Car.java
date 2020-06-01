package com.edu.cedesistemas.oop.model.vehicle;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Car implements Vehicle, Comparable<Car>{
    protected String id;
    protected double traveledKms;
    protected final String name;
    protected final String type;
    protected final double speed;
    private final List<Movement> movements;
    private LocalDateTime lastMaintenanceDate;

    public Car(double speed, String name, String type) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.speed = speed;
        movements = new ArrayList<>();
    }

    public abstract void tank();

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Movement move(Point p1, Point p2) {
        double distance = Point.distance(p1, p2);
        traveledKms += distance;
        double time = distance / speed;
        Segment s = new Segment(p1, p2);
        Movement movement = new Movement(s, time);
        movements.add(movement);
        return movement;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getTraveledKms() {
        return traveledKms;
    }

    @Override
    public List<Movement> getMovements() {
        return movements;
    }

    @Override
    public LocalDateTime getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    @Override
    public void setLastMaintenanceDate(LocalDateTime lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    // Lesson 3 -- Comparable
    @Override
    public int compareTo(Car car) {
        Double mySpeed =  this.speed;
        Double otherSpeed = car.speed;
        return mySpeed.compareTo(otherSpeed);
    }

    // Lesson 2 -- Inner classes
    // Lesson 3 -- Comparator
    public static class CarComparator implements Comparator<Car> {
        @Override
        public int compare(Car car1, Car car2) {
            // What car is slower?
            Double speed1 = car1.speed;
            Double speed2 = car2.speed;
            return speed2.compareTo(speed1);
        }
    }

    // Lesson 4 -- Sets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
