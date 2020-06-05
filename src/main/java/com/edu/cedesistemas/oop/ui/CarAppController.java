package com.edu.cedesistemas.oop.ui;

import com.edu.cedesistemas.oop.CarSimulator;
import com.edu.cedesistemas.oop.model.vehicle.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class CarAppController implements Initializable {
    @FXML
    private LineChart<Double, Double> lineGraph;

    @FXML
    private Button runButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtMovements;

    @FXML
    private TextArea txtStats;

    private CarGraph vehicleGraph;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        vehicleGraph = new CarGraph(lineGraph, 10);
        lineGraph.setVisible(true);
    }

    @FXML
    private void handleRunButtonAction(final ActionEvent event) {
        int n =Integer.parseInt(txtNumber.getText());
        int m = Integer.parseInt(txtMovements.getText());
        List<Car> cars = CarSimulator.simulate(n, m);

        for (Car car : cars) {
            plotLines(car);

            double speed = car.getSpeed();
            double distance = car.getTraveledKms();
            double time = distance / speed;

            String text = car.getName() + "\n"
                    + "-----------" + "\n"
                    + "speed: " + speed + "\n"
                    + "total distance: " + distance + "\n"
                    + "time: " + time + "\n"
                    + "-----------" + "\n\n";
            txtStats.appendText(text);
        }
        Collections.sort(cars);
    }

    @FXML
    private void handleClearButtonAction(final ActionEvent event) {
        clear();
    }

    private void plotLines(Car car) {
        if (lineGraph.isVisible()) {
            vehicleGraph.plotMovements(car);
        }
    }

    private void clear() {
        if (lineGraph.isVisible()) {
            vehicleGraph.clear();
            txtStats.clear();
        }
    }
}