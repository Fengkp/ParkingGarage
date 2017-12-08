package person;

import vehicle.Vehicle;

import java.util.Iterator;
import java.util.LinkedList;

public class Customer extends Person {
    private Vehicle vehicle;

    public Customer(String firstName, String lastName, String licensePlate) {
        super.firstName = firstName;
        super.lastName = lastName;
        this.vehicle = new Vehicle(firstName, lastName, licensePlate);
        this.vehicle.setParked(true);
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

}
