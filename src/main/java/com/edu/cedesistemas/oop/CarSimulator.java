package com.edu.cedesistemas.oop;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.vehicle.Car;
import com.edu.cedesistemas.oop.model.vehicle.ElectricCar;
import com.edu.cedesistemas.oop.model.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarSimulator {
    private static final  String[] CAR_NAMES = {"mazda","audi","ford","ranault","mercedes","fiat","chevrolet","bmw"};

    private static String pickName(int index) {
        return CAR_NAMES[index];
    }

    public static List<Car> simulate(int n, int movements) {
        if(n > CAR_NAMES.length){
            n = CAR_NAMES.length;
        }

        List<Car> result = new ArrayList<>();

        for(int i = 0; i<n; i++){
            int bound = 100;
            int speed =new Random().nextInt(128 -1) +1;
            String name = pickName(i);
            Car car = new ElectricCar(speed, name, 40);

            Point startingPoint = Point.of(0,0);
            Point destinationPoint = Point.of(bound,bound);
            Point p = Point.random(bound);

            car.move(startingPoint,p);

            Point lasPoint = p;

            for(int j=0;j<movements;j++){
                p = Point.random(bound);
                car.move(lasPoint,p);
                lasPoint = p;

            }

            car.move(lasPoint,destinationPoint);
            applyPenalty(car);
            result.add(car);
        }

        return result;
    }



    public static void applyPenalty(Car car) {
        double penaltyTime = 0.25; // 15 minutos
        if(car.getLastMaintenanceDate() == null){
            penaltyTime = penaltyTime * 2;
        }else {
            LocalDateTime lastMaintenance = car.getLastMaintenanceDate();
            LocalDateTime twoMonthsAgo = LocalDateTime.now().minusMonths(2);

            if (lastMaintenance.isBefore(twoMonthsAgo)) {
                penaltyTime = penaltyTime + 0.16666;
            }
        }

            if(car.isEmpty()){
                penaltyTime = penaltyTime + 0.08333;
                Random random = new Random();
                int gallons = random.nextInt(5-3) +3;
                car.tank(gallons);
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

    public static void main(String[] args) {
        List<Car> simulation = CarSimulator.simulate(4,5);


    }
}
