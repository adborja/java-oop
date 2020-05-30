package Vehicle;

public class DieselCar extends Car{

    public DieselCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("tanquear carro disel");
    }


    @Override
    public void power() {
        System.out.println("Cargar carro");
    }
}
