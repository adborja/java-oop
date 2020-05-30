package Vehicle;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDateTime;
import java.util.List;

public interface Vehicle {

    double move(Point x, Point y);
    List<Segment> getMovement();
    void power();
    double getSpeed();
    void setLastMaintenanceDate(LocalDateTime date);
    LocalDateTime getLastMaintenanceDate();
    double getTraveledKms();
    String getName();
    String getId();
    void setId(String a);

}
