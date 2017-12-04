import garage.Lot;
import vehicle.Vehicle;

public class Test {

    public static void main(String[] args) {

        Lot lot1 = new Lot();

        System.out.println();

        Vehicle vehicle1 = new Vehicle("12AB34");
        lot1.parkVehicle(vehicle1);
        System.out.println();
        for (int i = 10; i < 100; i++) {
            lot1.parkVehicle(new Vehicle(Integer.toString(i)));
        }
        System.out.println();

        lot1.returnSpace("44");
    }
}
