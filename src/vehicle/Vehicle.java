package vehicle;

public class Vehicle {
    private String licensePlate;
    private String owner;
    private boolean isParked;

    public Vehicle() {}

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setParked(boolean isParked) {
        this.isParked = isParked;
    }
}
