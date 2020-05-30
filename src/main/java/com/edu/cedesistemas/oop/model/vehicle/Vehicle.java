package com.edu.cedesistemas.oop.model.vehicle;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface Vehicle {

    double move(Point a, Point b);
    List<Segment> getMovements();
    void power();
    double getSpeed();
    void setLastMaintenanceDate(LocalDateTime dateTime);
    LocalDateTime getLastMaintenanceDate();
    double getTraveledKms();
    String getName();
    String getId();
    void setId(String id);

}
