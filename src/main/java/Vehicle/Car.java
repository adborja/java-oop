package Vehicle;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.security.ProtectionDomain;
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

    public Car(double speed, String name, String type){
        this.speed = speed;
        this.name = name;
        this.type = type;
    }

    @Override
    public double move(Point x, Point y) {
        return 0;
    }

    @Override
    public List<Segment> getMovement() {
        return movements;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setLastMaintenanceDate(LocalDateTime date) {
        lastMaintenanceDate = date;

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
    public void setId(String a) {
        this.id = a;
    }
        public abstract void tank();
}
