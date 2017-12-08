package vehicle;

public class Vehicle {
    private String licensePlate;
    private String owner;
    private boolean isParked;

    public Vehicle(String firstName, String lastName, String licensePlate) {
        this.owner = firstName + " " + lastName;
        this.licensePlate = licensePlate;
    }

    public void setParked(boolean isParked) {
        this.isParked = isParked;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }
}
