package Vehicle;

public class GasCar extends Car{
    public GasCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    @Override
    public void tank() {
        System.out.println("Tanquear carro a gas");
    }

    @Override
    public void power() {
        System.out.println("Cargar carro con gas");
    }
}
