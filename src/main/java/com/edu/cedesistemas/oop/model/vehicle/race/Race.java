package com.edu.cedesistemas.oop.model.vehicle.race;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.vehicles.Car;
import com.edu.cedesistemas.oop.model.vehicles.ElectricCar;

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

    public <T extends PitStop> void pits(PitStop<T> pitStop) {
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
        int bound = 100;
        //Point pt = new Point(0,0);
        //Point pt2 = new Point(0,0);
        for (int i=0; i < cars.size(); i++){
            int speed = new Random().nextInt(120 - 1) + 1;
            String name = "";
            Car car = new RaceCar(speed, "CARRO", 12, "", RaceCar.Team.MCLAREN);
            //Point startingPoint = pt.of(0,0);
            Point startingPoint = Point.of(0,0);
            //Point p = pt2.random(bound);
            Point p = Point.random(bound);
        }
    }

    public T getWinner() {
        T winner = null;
        // Obtener el ganador
        return winner;
    }
}
