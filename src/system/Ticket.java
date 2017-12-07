package system;

import person.Customer;
import system.Invoice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket extends Invoice {
    private Customer customer;
    private String id;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private boolean isActive;

    public Ticket(String firstName, String lastName, String licensePlate, String ticketNum) {
        this.customer = new Customer(firstName, lastName, licensePlate);
        this.isActive = true;
        this.arrivalTime = LocalDateTime.now();
        this.generateID(ticketNum);
    }

    public String getId() {
        return this.id;
    }

    public void setActive(boolean newActive) {
        super.isActive = newActive;
    }

    public void setDeparture() {
        this.departureTime = LocalDateTime.now();
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
