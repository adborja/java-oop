package com.edu.cedesistemas.oop.ui;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Segment;
import com.edu.cedesistemas.oop.model.vehicle.Car;
import com.edu.cedesistemas.oop.model.vehicle.Vehicle;
import javafx.scene.chart.XYChart;

import java.util.List;

public class CarGraph {
    private final XYChart<Double, Double> graph;
    private final double range;

    public CarGraph(final XYChart<Double, Double> graph, final double range) {
        this.graph = graph;
        this.range = range;

        Point start = Point.of(0, 0);
        Point end = Point.of(100, 100);
        final XYChart.Series<Double, Double> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(start.getX(), start.getY()));
        series.getData().add(new XYChart.Data<>(end.getX(), end.getY()));
        series.setName("straight");

        graph.getData().add(series);
    }

    public void plotMovements(Car v) {
        final XYChart.Series<Double, Double> series = new XYChart.Series<>();
        series.setName(v.getName());

        List<Vehicle.Movement> movements = v.getMovements();

        movements.forEach(m -> {
            Segment s = m.getSegment();
            series.getData().add(new XYChart.Data<>(s.getP1().getX(), s.getP1().getY()));
            series.getData().add(new XYChart.Data<>(s.getP2().getX(), s.getP2().getY()));
        });
        graph.getData().add(series);
    }

    private void plotPoint(final double x, final double y,
                           final XYChart.Series<Double, Double> series) {
        series.getData().add(new XYChart.Data<>(x, y));
    }

    public void clear() {
        graph.getData().clear();
    }
}
