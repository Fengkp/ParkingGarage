package garage;

import vehicle.Vehicle;

public class Space {
    private String id;
    private boolean isActive;
    private Vehicle currentVehicle;

    public Space(String id) {
        this.id = id;
        this.isActive = false;
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
        this.isActive = active;
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
