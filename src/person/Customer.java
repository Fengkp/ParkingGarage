package person;

import vehicle.Vehicle;

import java.util.Iterator;
import java.util.LinkedList;

public class Customer extends Person {
    private LinkedList<Vehicle> vehicles;
    private Vehicle currentVehicle;

    public Customer(String firstName, String lastName, String licensePlate) {
        super.firstName = firstName;
        super.lastName = lastName;
        this.vehicles = new LinkedList<>();
        this.vehicles.add(new Vehicle(licensePlate));
        this.currentVehicle = vehicles.getFirst();
    }

    public String getFirstName() {
        return super.firstName;
    }

    public String getLastName() {
        return super.lastName;
    }

    public Vehicle getCurrentVehicle() {
        return this.currentVehicle;
    }

    public void addVehicle(String licensePlate) {
        vehicles.add(new Vehicle(licensePlate));
    }

    public boolean findVehicle(String key) {
        Iterator<Vehicle> vehicle = this.vehicles.iterator();
        while(vehicle.hasNext()) {
            this.currentVehicle = vehicle.next();
            if (this.currentVehicle.getLicensePlate().equals(key))
                return true;
        }
        return false;
    }

}
