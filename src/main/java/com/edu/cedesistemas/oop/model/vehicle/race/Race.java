package com.edu.cedesistemas.oop.model.vehicle.race;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;
import com.edu.cedesistemas.oop.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Race<T extends RaceCar> {
    private final List<T> cars;

    public Race() {
        cars = new ArrayList<>();
    }

    public void addCar(T car) {
        cars.add(car);
    }

    public void pits(PitStop<T> pitStop) {
        T car = pitStop.getCar();
        pitStop.tank(10);
        pitStop.adjust();
        pitStop.changeTires();
        pitStop.repair();

        // Adding additional time to car ...
        double extraTime = new Random().nextDouble() * 0.5;
        double time = car.getMovements().get(0).getTime();
        car.getMovements().get(0).setTime(time + extraTime);

        System.out.println("car " + car.getName() + " finishing pits. Extra time: " + extraTime);
    }

    public void race() {
        System.out.println("running race with: " + cars.size() + " cars");
        for (T car : cars) {
            Segment s = new Segment(Point.of(0, 0), Point.of(100, 100));
            double time = s.getValue() / car.getSpeed();
            Vehicle.Movement movement = new Vehicle.Movement(s, time);
            car.getMovements().add(movement);
        }
    }

    public T getWinner() {
        T winner = null;
        double minTime = 10;
        for (T car : cars) {
            Vehicle.Movement movement = car.getMovements().get(0);
            if (movement.getTime() < minTime) {
                minTime = movement.getTime();
                winner = car;
            }
        }
        return winner;
    }
}
