package system;

import person.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private Customer customer;
    private String id;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private boolean isActive;

    public Ticket(Customer newCustomer, String ticketNum) {
        this.customer = newCustomer;
        this.isActive = true;
        this.arrivalTime = LocalDateTime.now();
        this.generateID(ticketNum);
    }

    public String getId() {
        return this.id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setDeparture() {
        this.departureTime = LocalDateTime.now();
        this.isActive = false;
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
