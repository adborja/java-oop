package com.edu.cedesistemas.oop.model.ui;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.vehiculo.Car;
import com.edu.cedesistemas.oop.model.vehiculo.ElectricCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarSimulator {

        private static final String[] CAR_NAMES = {"mazda", "audi", "ford", "renault",
                "mercedes", "fiat", "chevrolet", "bmw"};
        private static String pickName(int index) {
            return CAR_NAMES[index];
        }
        public static List<Car> simulate(int n, int movements) {
            if (n > CAR_NAMES.length) {
                n=CAR_NAMES.length;
            }
            int bound =100;
            List<Car> result = new ArrayList<>();
            for (int i=0;i<n;i++){

                int speed = new Random().nextInt(120 -1)+1;
                String name = pickName(i);
                String type = "";

                Car car = new ElectricCar(speed,name,type);

                Point startingPoint = Point.of(0,0);
                Point destinationPoit = Point.of(bound,bound);

                Point p = Point.random(bound);
                car.move(startingPoint,p);
                Point lastPoint =p;

                for (int j=0;j<movements;j++){
                    p = Point.random(bound);
                    car.move(lastPoint,p);
                    lastPoint =p;
                }
                //Go to destination ...
                car.move(lastPoint,destinationPoit);

                //Apply penalty ...
                applyPenalty(car);

                result.add(car);

            }
            return result;
        }
        public static void applyPenalty(Car car) {
            double penaltyTime = 0.25; // 15 minutos
            /*if (car.getLastMaintenanceDate()==null) {
                penaltyTime = penaltyTime * 2;
                System.out.println("car" +car.getName()+"penalidad por no tener mantenimiento");
            } else {
                LocalDateTime lastMaintenance = car.getLastMaintenanceDate();
                LocalDateTime twoMonthsAgo = LocalDateTime.now().minusMonths(2);
                if (lastMaintenance.isBefore(twoMonthsAgo)) {
                    penaltyTime = penaltyTime + 0.16666;
                }
            }
            if (car.getTraveledKms() >= 200) {
                penaltyTime = penaltyTime + 0.16666;
                System.out.println("car" +car.getName()+"penalidad por kilometraje");

            }
            if (car.isEmpty()) {
                penaltyTime = penaltyTime + 0.08333;
                Random random = new Random();
                int gallons = random.nextInt(5 - 3) + 3;
                //car.tank(gallons);
                System.out.println("car" +car.getName()+"penalidad por tanque vacio");

            }

            if (penaltyTime > 0.25) {
                // Aplicar penalidad ...
                List<Vehicle.Movement> movements = car.getMovements();
                for (Vehicle.Movement m : movements) {
                    double time = m.getTime();
                    time = time + penaltyTime;
                    m.setTime(time);
                }
            }*/
        }
        public static void main(String[] args) {
        }

}
