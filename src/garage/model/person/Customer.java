package garage.model.person;

import garage.model.vehicle.Vehicle;

public class Customer extends Person {
    private Vehicle vehicle;

    public Customer(String firstName, String lastName, String licensePlate) {
        super.firstName = firstName;
        super.lastName = lastName;
        vehicle = new Vehicle(firstName, lastName, licensePlate);
        vehicle.setParked(true);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getName() {
        return super.firstName + " " + super.lastName;
    }

}
