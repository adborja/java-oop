package com.edu.cedesistemas.oop.model.vehiculo;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDateTime;
import java.util.List;

public interface Vehicle {
    double move(Point x, Point y);

    List<Segment> getMovements();
    void power();
    double getSpeed();
    void setLastMainteinceDate(LocalDateTime date);
    LocalDateTime setLastMainteinceDate();
    double getTraveledkms();
    String getName();
    String getId();
    void setId(String id);


}
