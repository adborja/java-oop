package model.vehicle;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDateTime;
import java.util.List;

public interface Vehicle {

    double move(Point a, Point B);

    List<Segment> getMovements();

    void power();

    double getSpeed();

    void setLastMaintenanceDate(LocalDateTime date);

    LocalDateTime getlastMaintenanceDate();

    double getTraveledKms();

    String getName();

    String getId();

    void setId(String C);

}
