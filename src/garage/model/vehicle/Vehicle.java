package garage.model.vehicle;

import java.io.Serializable;

/**
 * The Vehicle class stores any information associated with the customer.
 * It also keeps track of its associated space number.
 *
 * @author Feng Parra
 * @version 1.0 Dec. 17, 2017.
 */
public class Vehicle implements Serializable {
    private String licensePlate;
    private String owner;
    private String spaceNum;
    private boolean isParked;

    /**
     * Constructs and initializes the Vehicle class.
     * @param firstName Associated customer's first name.
     * @param lastName Associated customer's last name.
     * @param licensePlate The vehicles license plate number and ID.
     */
    public Vehicle(String firstName, String lastName, String licensePlate) {
        owner = firstName + " " + lastName;
        this.licensePlate = licensePlate;
    }

    /**
     * Sets status of the vehicle.
     * Whether parked or not.
     * @param parked True or false value based on ticket activity.
     */
    public void setParked(boolean parked) {
        isParked = parked;
    }

    /**
     * Sets associated space number.
     * @param spaceNum Space number assigned to vehicle.
     */
    public void setSpaceNum(String spaceNum) {
        this.spaceNum = spaceNum;
    }

    /**
     * Returns license plate.
     * @return
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Returns associated space number.
     * @return
     */
    public String getSpaceNum() {
        return spaceNum;
    }

    @Override
    public int hashCode() {
        return licensePlate.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return licensePlate.toLowerCase().equals(o);
    }
}
