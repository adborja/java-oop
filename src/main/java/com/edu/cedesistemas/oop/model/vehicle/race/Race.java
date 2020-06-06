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
        // tanquear el carro
        pitStop.tank(30);
        // ajustar el carro
        pitStop.adjust();
        // cambiar llantas
        pitStop.changeTires();
        // reparar el carro
        pitStop.repair();

        // Adding additional time to car ...
        T car = pitStop.getCar();
        double extraTime = new Random().nextDouble() * 0.5;
        double time = car.getMovements().get(0).getTime();
        car.getMovements().get(0).setTime(time + extraTime);

        // Reasignar el tiempo del movimiento del carro
        System.out.println("car " + car.getName() + " finishing pits. Extra time: " + extraTime);
    }

    public void race() {
        System.out.println("running race with: " + cars.size() + " cars");
        /*
        Para cada carro:
            crear un segment desde (0, 0) hasta (100, 100)
            calcular el tiempo del segmento: t = x / v --> Formula de velocidad
            crear un movimiento
            agregar el movimiento al carro
        * */

        int boundIni = 0;
        int boundEnd = 100;
        for (int i=0; i < cars.size(); i++){
            Segment segment = new Segment(Point.of(boundIni,boundIni), Point.of(boundEnd,boundEnd));
            double timeSegment = segment.getValue() / cars.get(i).getSpeed();
            Vehicle.Movement movement = new Vehicle.Movement(segment, timeSegment);
            cars.get(i).getMovements().add(movement);
        }
    }

    public T getWinner() {
        T winner = null;
        // Obtener el ganador
        double timeMin = 60;
        for (int i=0; i < cars.size(); i++){
            Vehicle.Movement movement = cars.get(i).getMovements().get(0);
            if (movement.getTime() < timeMin){
                timeMin = movement.getTime();
                winner = cars.get(i);
            }
        }
        return winner;
    }
}
