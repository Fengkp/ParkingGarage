package garage.model.garage;

import garage.model.vehicle.Vehicle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Space implements Serializable {
    private String id;
    private boolean active;
    private Vehicle currentVehicle;

    public Space(String id) {
        this.id = id;
        active = false;
    }

    public String getID() {
        String spaceNum = id.toString();
        return spaceNum;
    }

    public void addVehicle(Vehicle newVehicle) {
        this.currentVehicle = newVehicle;
    }

    public void removeVehicle() {
        this.currentVehicle = null;
    }

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
