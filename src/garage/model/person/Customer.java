package garage.model.person;

import garage.model.vehicle.Vehicle;

import java.io.Serializable;

/**
 * The Customer class stores all the information associated with the customer.
 * It also keeps track of the customer's vehicle.
 *
 * @author Feng Parra
 * @version 1.0 Dec. 17, 2017.
 */
public class Customer extends Person implements Serializable {
    private Vehicle vehicle;
    private String phoneNum;

    /**
     * Constructs and initializes the Customer class.
     * @param firstName Customer's first name.
     * @param lastName Customer's last name.
     * @param licensePlate Associated vehicles license plate number.
     * @param phoneNum Customer's primary contact information.
     */
    public Customer(String firstName, String lastName, String licensePlate, String phoneNum) {
        super.firstName = firstName;
        super.lastName = lastName;
        this.phoneNum = phoneNum;
        vehicle = new Vehicle(firstName, lastName, licensePlate);
        vehicle.setParked(true);
    }

    /**
     * Returns associated vehicle information.
     * @return
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Returns the customer's full name as a String object.
     * @return
     */
    public String getName() {
        return super.firstName + " " + super.lastName;
    }

    /**
     * REturns the customer's phone number.
     * @return
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    @Override
    public int hashCode() {
        return getName().toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return getName().toLowerCase().equals(o);
    }
}
