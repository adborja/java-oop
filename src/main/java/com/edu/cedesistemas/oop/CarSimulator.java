package com.edu.cedesistemas.oop;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.vehicles.Car;
import com.edu.cedesistemas.oop.model.vehicles.ElectricCar;
import com.edu.cedesistemas.oop.model.vehicles.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarSimulator {

    private static final String[] CARS_NAMES = {"mazda", "audi", "ford", "renault",
            "mercedes", "fiat", "chevrolet", "bmw"};

    private static String pickName(int index){
        return CARS_NAMES[index];
    }

    public static List<Car> simulate(int n, int movements){

        if(n >  CARS_NAMES.length){
            n = CARS_NAMES.length;
        }
        List<Car> result = new ArrayList<>();
        int bound = 100;
        for(int i = 0; i < n; i++){
            int speed = new Random().nextInt(120 -1) + 1;
            String name = pickName(i);
            Car car = new ElectricCar(speed, name, 40);
            Point statrtingPoint = Point.of(0,0);
            Point destinationgPoint = Point.of(bound,bound);
            Point p = Point.random(bound);
            car.move(statrtingPoint, p);
            Point lastPoint = p;
            for(int j = 0; j < movements; j++){
                p = Point.random(bound);
                car.move(lastPoint, p);
                lastPoint = p;
            }
            car.move(lastPoint,destinationgPoint);
            applyPenalty(car);
            result.add(car);
        }

        return null;
    }

    public static void applyPenalty(Car car){
        double penaltyTime=0.25;
        if(car.getLastMaintenanceDate() == null){
            penaltyTime = penaltyTime * 2;
        }else{
            LocalDateTime lasMaintenance = car.getLastMaintenanceDate();
            LocalDateTime towMonthsAgo = LocalDateTime.now();
            if(lasMaintenance.isBefore(towMonthsAgo)){
                penaltyTime = penaltyTime + 0.16666;
            }
        }
        if(car.getTraveledKms() >= 200){
            penaltyTime = penaltyTime + 0.16666;
        }
        if(car.isEmpty()){
            penaltyTime = penaltyTime + 0.0833;
            Random random = new Random();
            int galons = random.nextInt(5 - 3) + 3;
            car.tank(galons);
        }
        if(penaltyTime > 0.25){
            List<Vehicle.Movement> movements = car.getMovements();
            for(Vehicle.Movement m : movements){
                double time = m.getTime();
                time = time + penaltyTime;
                m.setTime(time);

            }
        }

    }

    public static void main (String[] args){

    }
}

