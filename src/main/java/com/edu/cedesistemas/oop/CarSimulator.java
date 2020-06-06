package com.edu.cedesistemas.oop;
import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;
import com.edu.cedesistemas.oop.model.vehicle.Car;
import com.edu.cedesistemas.oop.model.vehicle.FuelCar;
import com.edu.cedesistemas.oop.model.vehicle.Vehicle;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarSimulator {
    private static final String[] CAR_NAMES =
            {"mazda", "audi", "ford", "renault", "mercedes", "fiat", "chevrolet", "bmw"};
    private static String pickName(int index) {
        return CAR_NAMES[index];
    }
    public static List<Car> simulate(int n, int movements) {
        if (n > CAR_NAMES.length) {
            n = CAR_NAMES.length;
        }
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int bound = 100;
            int speed = new Random().nextInt(120 - 1) + 1;
            String name = pickName(i);
            Car car = new FuelCar(speed, name, 40, "4WD");
            Point startingPoint = Point.of(0, 0);
            Point destinationPoint = Point.of(bound, bound);
            Point p = Point.random(bound);
            car.move(startingPoint, p);
            Point lastPoint = p;
            for (int j = 0; j < movements; j++) {
                p = Point.random(bound);
                car.move(lastPoint, p);
                lastPoint = p;
            }
            // Go to destination
            car.move(lastPoint, destinationPoint);
            // Apply penalty (if any) ...
            applyPenalty(car);
            // Add car to list
            cars.add(car);
        }
        return cars;
    }
    public static void applyPenalty(Car car) {
        double penaltyTime = 0.25;
        // Applying penalty due last maintenance date ...
        LocalDateTime twoMonthsAgo = LocalDateTime.now().minusMonths(2);
        LocalDateTime maintenance = car.getLastMaintenanceDate();
        if (maintenance != null) {
            if (maintenance.isBefore(twoMonthsAgo)) {
                System.out.println("applying penalty to " + car.getName() + " due maintenance date");
                penaltyTime = 0.1666 + penaltyTime;
            }
        } else {
            System.out.println("applying penalty to " + car.getName() + " due no maintenance date");
            penaltyTime = penaltyTime + 2 * penaltyTime;
        }
        // Applying penalty due traveled kms ...
        double traveledKms = car.getTraveledKms();
        if (traveledKms > 200) {
            System.out.println("applying penalty to " + car.getName() +" due traveled kms");
            penaltyTime = penaltyTime + 0.1666;
        }
        // Applying penalty for tanking time ...
        boolean empty = car.isEmpty();
        if (empty) {
            System.out.println("applying penalty to " + car.getName() +" due tanking");
            penaltyTime = penaltyTime + 0.0833;
            int gallons = new Random().nextInt(5 - 3) + 3;
            car.tank(gallons);
        }
        // Applying penalty to all car movements ...
        List<Vehicle.Movement> movements = car.getMovements();
        for (Vehicle.Movement m : movements) {
            double time = m.getTime();
            m.setTime(time + penaltyTime);
        }
        System.out.println("total penalty time to [" + car.getName() + "] : " + penaltyTime + " hours");
    }
    public static void main(String[] args) {
        System.out.println("Running simulation ...");
        List<Car> cars = simulate(4, 3);
        for (Car car : cars) {
            System.out.println("Car [" + car.getName() + "] ...");
            double speed = car.getSpeed();
            double totalTime = 0;
            List<Vehicle.Movement> movements = car.getMovements();
            for (Vehicle.Movement m : movements) {
                Segment segment = m.getSegment();
                double time = m.getTime();
                totalTime += time;
                String description = String.format("%s took %s hours to move %s (%s kms) with speed %s k/h",
                        car.getName(), time, segment, segment.length(), speed);
                System.out.println(description);
            }
            System.out.println("Total time for [" + car.getName() + "]: " + totalTime + " hours");
        }
    }
}