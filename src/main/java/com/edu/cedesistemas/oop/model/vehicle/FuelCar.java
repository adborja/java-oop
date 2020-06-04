package com.edu.cedesistemas.oop.model.vehicle;

public class FuelCar extends Car {
    private CylinderType cylinderType;
    private final String type;

    public FuelCar(double speed, String name, double consumption, String type) {
        super(speed, name, consumption);
        this.type = type;
    }

    public void setCylinderType(CylinderType cylinderType) {
        this.cylinderType = cylinderType;
    }

    public CylinderType getCylinderType() {
        return cylinderType;
    }

    @Override
    public void tank(int quantity) {
        System.out.println("fuel car tanking");
        this.currentTankQuantity += quantity;
    }

    @Override
    public void power() {
        System.out.println("car powering up using fuel");
    }

    // Lesson 2 -- Inner classes
    public class Engine {
        String engineType;

        public void setEngine() {
            if (FuelCar.this.type.equals("4WD")) {
                if (FuelCar.this.getName().equals("ford")) {
                    this.engineType = "bigger";
                } else {
                    this.engineType = "smaller";
                }
            } else {
                this.engineType = "bigger";
            }
        }

        public String getEngineType() {
            return this.engineType;
        }
    }
}
