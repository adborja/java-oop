package com.edu.cedesistemas.oop.model.vehicles;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;

import java.time.LocalDateTime;
import java.util.List;

public interface Vehicle {
    Movement move(Point p1, Point p2);//Retorna el segmento entre 2 puntos
    List<Movement> getMovements(); //lista de todos los movimientos que hizo el vehiculo
    void power();
    double getSpeed();
    LocalDateTime getLastMaintenanceDate();
    void setLastMaintenanceDate(LocalDateTime date);
    double getTraveledKms();
    String getName();
    String getId();
    void setId(String id);

    // Lesson 2 -- Inner classes
    class Movement {
        private final Segment segment;
        private double time;

        public Movement(Segment segment, double time) {
            this.segment = segment;
            this.time = time;
        }

        public double getTime() {
            return time;
        }

        public void setTime(double time) {
            this.time = time;
        }

        public Segment getSegment() {
            return segment;
        }
    }
}
