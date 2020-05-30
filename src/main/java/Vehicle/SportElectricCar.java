package Vehicle;

public class SportElectricCar extends ElectricCar{

    public SportElectricCar(double speed, String name, String type) {
        super(speed, name, type);
    }

    //Polimorfismo si se quiere


    @Override
    public void power() {
        System.out.println("encender carro electrico sport");
    }
}
