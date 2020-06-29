package com.edu.cedesistemas.oop.model.vehicle;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Car implements Vehicle, Comparable<Car> {
    protected String id;
    protected double traveledKms;
    protected final String name;
    protected final double speed;
    protected final double consumption;
    protected int currentTankQuantity;
    private final List<Movement> movements;
    private LocalDateTime lastMaintenanceDate;
    private boolean empty;

    public Car(double speed, String name, double consumption) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.consumption = consumption;
        this.speed = speed;
        movements = new ArrayList<>();

        // Getting random value for initial tank quantity;
        this.currentTankQuantity = new Random().nextInt(5 - 3) + 3;
    }

    public abstract void tank(int quantity);

    public int getCurrentTankQuantity() {
        return currentTankQuantity;
    }

    public void consume(double distance) {
        System.out.println(getName() + " consuming. Distance: " + distance +"." +
                " Current tank quantity: " + currentTankQuantity);
        if (empty) {
            return;
        }

        int quantity = (int) (distance / this.consumption);
        if (this.currentTankQuantity >= quantity) {
            this.currentTankQuantity -= quantity;
        } else {
            currentTankQuantity = 0;
            empty = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

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
        double distance = p1.distance(p1, p2);
        traveledKms += distance;
        double time = distance / speed;
        consume(distance);
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

    @Override
    public int compareTo(Car car) {
        Double t1 = this.traveledKms / this.speed;
        Double t2 = car.traveledKms / car.speed;
        return t1.compareTo(t2);
    }

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