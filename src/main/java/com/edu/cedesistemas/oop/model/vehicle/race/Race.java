package com.edu.cedesistemas.oop.model.vehicle.race;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;
import com.edu.cedesistemas.oop.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Race<T> {
    private final List<T> cars;

    public Race() {
        cars = new ArrayList<>();
    }

    public void addCar(T car) {
        cars.add(car);
    }

    public <T extends RaceCar> void pits(PitStop<T> pitStop) {
        // tanquear el carro
        pitStop.tank(10);

        // ajustar el carro
        pitStop.adjust();
        // cambiar llantas
        pitStop.changeTires();
        // reparar el carro
        pitStop.repair();

        // Adding additional time to car ...
        T car = pitStop.getCar();
        double extraTime = new Random().nextDouble() * 0.5;
        // Reasignar el tiempo del movimiento del carro
        Vehicle.Movement movements = car.getMovements().get(0);
        movements.setTime(movements.getTime() + extraTime);

        System.out.println("car " + car.getName() + " finishing pits. Extra time: " + extraTime);
    }

    public void race() {
        System.out.println("running race with: " + cars.size() + " cars");

        for (T t : cars) {
            int bound = 100;
            Point startingPoint = Point.of(0, 0);
            Point destinationPoint = Point.of(bound, bound);
            int speed = new Random().nextInt(120 - 1) + 1;

            Segment segment = new Segment(startingPoint,destinationPoint);


            double time = bound/speed;


            Vehicle.Movement movement = new Vehicle.Movement(segment,time);

            ((RaceCar) t).getMovements().add(movement);

        }

        /*
        Para cada carro:
            crear un segment desde (0, 0) hasta (100, 100)
            calcular el tiempo del segmento: t = x / v --> Formula de velocidad
            crear un movimiento
            agregar el movimiento al carro
        * */
    }

    public T getWinner() {
        T winner = null;
        // Obtener el ganador
        return winner;
    }
}
