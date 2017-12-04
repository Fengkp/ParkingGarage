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

    public String getLicensePlate() {
        return this.licensePlate;
    }


    @Override
    public int hashCode() {
        return this.licensePlate.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this.licensePlate.equals(o);
    }
}
