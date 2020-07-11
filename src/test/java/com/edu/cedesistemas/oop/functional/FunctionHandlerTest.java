package com.edu.cedesistemas.oop.functional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import com.edu.cedesistemas.oop.model.geometry.Circle;
import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Rectangle;
import com.edu.cedesistemas.oop.model.vehicle.Car;
import com.edu.cedesistemas.oop.model.vehicle.DieselCar;
import com.edu.cedesistemas.oop.model.vehicle.ElectricCar;
import com.edu.cedesistemas.oop.model.vehicle.FuelCar;
import com.edu.cedesistemas.oop.model.vehicle.GasCar;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionHandlerTest {
    @Test
    public void testGetArea() {
        double radius = 10;
        Circle circle = new Circle(radius);
        ShapeMultiplier<Circle, Double> shapeMultiplier = (c, v) -> c.scale(v);
        Circle multiplied = FunctionHandler.getMultiplied(shapeMultiplier, circle, 200D);
        Double area = multiplied.area();

        double circleArea = Math.PI * Math.pow(radius, 2);
        double newCircleArea = Math.PI * Math.pow(multiplied.getRadius(), 2);

        System.out.println(circleArea);
        assertThat(circle.area(), closeTo(circleArea, 0.001));
        assertThat(area, closeTo(newCircleArea, 0.001));
    }

    @Test
    public void testGetArea2() {
        Rectangle rectangle = new Rectangle(Point.of(0, 0), 10, 15);
        Function<Rectangle, Double> f = r -> r.area();
        Double area = FunctionHandler.applyFunction(f, rectangle);
        assertThat(area, equalTo(150D));
    }

    @Test
    public void testGetCarName() {
        Function<Car, String> f = c -> c.getName();
        String name = FunctionHandler.applyFunction(f, new ElectricCar(100, "honda", 60));
        assertThat(name, equalTo("honda"));
    }

    @Test
    public void testSort() {
        Circle c1 = new Circle(10);
        Circle c2 = new Circle(15);
        Circle c3 = new Circle(5);
        Circle c4 = new Circle(12);
        List<Circle> circles = Arrays.asList(c1, c2, c3, c4);

        Consumer<List<Circle>> sorter = l -> Collections.sort(l);
        FunctionHandler.consume(sorter, circles);

        assertThat(circles, contains(c3, c1, c4, c2));
    }

    @Test
    public void testCarMap() {
        Function<List<Car>, Map<String, List<Car>>> function = FunctionHandler.getCarMapper();

        Car c1 = new ElectricCar(80, "mazda", 50);
        Car c2 = new FuelCar(100, "renault", 50, "2WD");
        Car c3 = new ElectricCar(95, "audi", 50);
        Car c4 = new ElectricCar(110, "mazda", 50);
        Car c5 = new DieselCar(95, "mercedes", 50);
        Car c6 = new FuelCar(105, "mazda", 50, "2WD");
        Car c7 = new FuelCar(75, "renault", 50, "2WD");
        Car c8 = new ElectricCar(80, "ford", 50);
        Car c9 = new DieselCar(90, "mercedes", 50);
        Car c10 = new GasCar(100, "ford", 50, "2WD");

        List<Car> cars = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);

        Map<String, List<Car>> result = function.apply(cars);
        assertThat(result.size(), equalTo(5));
        assertThat(result.get("mazda"), contains(c1, c4, c6));
        assertThat(result.get("renault"), contains(c2, c7));
        assertThat(result.get("audi"), contains(c3));

    }
}
