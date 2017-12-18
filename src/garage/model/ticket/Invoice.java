package garage.model.ticket;

import javafx.beans.property.SimpleStringProperty;

import java.time.format.DateTimeFormatter;

/**
 * The Invoice class stores the customer's information.
 * This is the primary way to get extensive details on any given transaction.
 *
 * @author Feng Parra
 * @version 1.0 Dec. 18, 2017
 */
public class Invoice extends Record {
    private Ticket ticket;

    /**
     * Constructs and initializes the Invoice class based off of an existing ticket.
     * It sets the values of year and month and the rest are filled in by calling the initInvoice method.
     * @param ticket Ticket to be converted into invoice.
     */
    public Invoice(Ticket ticket) {
        this.ticket = ticket;
        year = new SimpleStringProperty(ticket.getDeparture().format(DateTimeFormatter.ofPattern("YYYY")));
        month = new SimpleStringProperty(ticket.getDeparture().format(DateTimeFormatter.ofPattern("MMM")));
        initInvoice();
    }

    /**
     * Assigns values to the invoice based on the ticket assigned.
     */
    public void initInvoice() {
        customerName = ticket.customerName;
        licensePlate = ticket.licensePlate;
        id = ticket.id;
        spaceNum = ticket.spaceNum;
        level = ticket.level;
        phoneNum = ticket.phoneNum;
        arrivalTime = ticket.arrivalTime;
        departureTime = ticket.departureTime;
        totalPay = ticket.totalPay;
        duration = ticket.duration;
        arrivalDate = new SimpleStringProperty(ticket.getArrival().format(DateTimeFormatter.ofPattern("MM/dd/YYYY")));
        departureDate = new SimpleStringProperty(ticket.getDeparture().format(DateTimeFormatter.ofPattern("MM/dd/YYYY")));
    }
}
