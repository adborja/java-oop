package Vehicle;

public class Fuel extends Car{

    public Fuel(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("fuel");
    }

    @Override
    public void power() {
        System.out.println("fuel");
    }
}
