package com.edu.cedesistemas.oop.model.vehicles;

import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public interface Vehicle {
    double move(Point p1, Point p2);
    List<Segment> getMovements();
    void power();
    double getSpeed();
    void setLastMantenanceDate(LocalDateTime date);
    LocalDateTime getLastMantenanceDate(LocalDateTime date);
    double getTraveledKms();
    String getName();
    String getId();
    void setId(String s1);
}
