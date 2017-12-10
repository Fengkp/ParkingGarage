package garage.model.ticket;

import garage.model.person.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private Customer customer;
    private String licensePlate;
    private String id;
    private String spaceNum;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private boolean isActive;

    public Ticket(Customer newCustomer, String ticketNum, String spaceNum) {
        customer = newCustomer;
        licensePlate = customer.getVehicle().getLicensePlate();
        this.spaceNum = spaceNum;
        isActive = true;
        arrivalTime = LocalDateTime.now();
        generateID(ticketNum);
    }

    public String getId() {
        return id;
    }

    public String getSpaceNum() {
        return spaceNum;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getArrival() {
        return arrivalTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ", " + arrivalTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDeparture() {
        departureTime = LocalDateTime.now();
        isActive = false;
    }

    public String toString() {
        return id + ", " + customer.toString() + ", " + licensePlate + ", " + arrivalTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ", " + arrivalTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }

    private void generateID(String ticketNum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String idSuffix = arrivalTime.format(formatter);
        this.id = ticketNum + idSuffix;
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
