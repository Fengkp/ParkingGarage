package person;

import vehicle.Vehicle;

import java.util.LinkedList;

public class Customer extends Person {
    private LinkedList<Vehicle> vehicles;
    private Vehicle primaryVehicle;
    private Vehicle currentVehicle;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        super.firstName = firstName;
        super.lastName = lastName;
    }

    public void addVehicle(Vehicle newVehicle) {
        if (vehicles.isEmpty())
            vehicles.addFirst(newVehicle);
        else
            vehicles.add(newVehicle);
    }

    public boolean findVehicle(String key) {
        return false;
    }

    public void removeVehicle(String key) {

    }

    public boolean setPrimaryVehicle(String key) {
        if (findVehicle(key)) {
            this.primaryVehicle = this.currentVehicle;
            return true;
        }
        return false;
    }
}
