package com.edu.cedesistemas.oop.model.vehicle;

// Lesson 2 -- Inner classes
public class Truck extends FuelCar {
    private Container container;

    public Truck(double speed, String name, String type) {
        super(speed, name, type);
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public void power() {
        System.out.println("truck powering up using fuel");
    }

    // Lesson 2 -- Inner classes
    public static class Container {
        private final double capacity;
        private final double width;
        private final double height;

        public Container(double capacity, double width, double height) {
            this.capacity = capacity;
            this.width = width;
            this.height = height;
        }

        public double getCapacity() {
            return capacity;
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }
    }
}
