package garage.model.garage;

import garage.model.vehicle.Vehicle;
import java.io.Serializable;

/**
 * The Space class stores a vehicle object and its information.
 *
 * @author Feng Parra
 * @version 1.0 Dec. 17, 2017.
 */
public class Space implements Serializable {
    private String id;
    private boolean active;
    private Vehicle currentVehicle;

    /**
     * Constructs and initializes the Space class.
     * It creates a new space when first initializing the level class.
     * @param id The space number.
     */
    public Space(String id) {
        this.id = id;
        active = false;
    }

    /**
     * Returns the spaces number as a String object.
     * @return
     */
    public String getID() {
        String spaceNum = id.toString();
        return spaceNum;
    }

    /**
     * Assigns a new vehicle to the space.
     * @param newVehicle
     */
    public void addVehicle(Vehicle newVehicle) {
        this.currentVehicle = newVehicle;
    }

    /**
     * Removes the assigned vehicle.
     */
    public void removeVehicle() {
        this.currentVehicle = null;
    }

    /**
     * Sets the activity level of the space.
     * If active then the space is occupied by a vehicle.
     * Otherwise the space is considered inactive with a boolean value of false.
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this.id.equals(o);
    }
}
