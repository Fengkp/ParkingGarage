package garage.model.vehicle;

public class Vehicle {
    private String licensePlate;
    private String owner;
    private String spaceNum;
    private boolean isParked;

    public Vehicle(String firstName, String lastName, String licensePlate) {
        owner = firstName + " " + lastName;
        this.licensePlate = licensePlate;
    }

    public void setParked(boolean parked) {
        isParked = parked;
    }

    public void setSpaceNum(String spaceNum) {
        this.spaceNum = spaceNum;
    }
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwner() {
        return owner;
    }

    public String getSpaceNum() {
        return spaceNum;
    }
}
