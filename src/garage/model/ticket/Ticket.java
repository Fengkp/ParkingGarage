package garage.model.ticket;

import garage.model.InvoiceSystem;
import garage.model.TicketSystem;
import garage.model.person.Customer;
import javafx.beans.property.SimpleStringProperty;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * The Ticket class stores the customer's information including their vehicle.
 * It is also logs the times of arrival and departure in order to calculate payment.
 *
 * @author Feng Parra
 * @version 1.0 Dec. 17, 2017.
 */
public class Ticket extends Record implements CalculatePayment {
    private Customer customer;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private double payment;
    private double totalDue;
    private double hoursStayed;
    private boolean isActive;

    /**
     * Constructs and initializes the Ticket class, creating a new customer in the process.
     * It will also log the arrival time of the customer as well as generate its on unique ID for the day.
     * @param newCustomer New customer associated with ticket.
     * @param ticketNum Unique ID to reference ticket.
     * @param spaceNum Space number assigned to the ticket.
     */
    public Ticket(Customer newCustomer, String ticketNum, String spaceNum) {
        customer = newCustomer;
        customerName = new SimpleStringProperty(customer.getName());
        customer.getVehicle().setParked(true);
        licensePlate = new SimpleStringProperty(customer.getVehicle().getLicensePlate());
        this.spaceNum = new SimpleStringProperty(spaceNum);
        level = new SimpleStringProperty(spaceNum.substring(0, 1));
        phoneNum = new SimpleStringProperty(customer.getPhoneNum());
        isActive = true;
        arrival = LocalDateTime.now();
        arrivalTime = new SimpleStringProperty(arrival.format(DateTimeFormatter.ofPattern("hh:mm a")));
        generateID(ticketNum);
    }

    /**
     * Returns associated customer object.
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the date and time of arrival.
     * @return
     */
    public LocalDateTime getArrival() {
        return arrival;
    }

    /**
     * Returns the date and time of departure.
     * @return
     */
    public LocalDateTime getDeparture() {
        return departure;
    }

    /**
     * Makes the ticket inactive.
     * Sets the departure time to calculate duration and then payment.
     */
    public void closeTicket() {
        setDeparture();
        calculateDuration();
        isActive = false;
        calculatePay();
        calculateTaxPay();
        customer.getVehicle().setParked(false);
        Invoice invoice = new Invoice(this);
        InvoiceSystem.getInstance().addInvoice(invoice);
    }

    /**
     * Sets the departure time.
     */
    public void setDeparture() {
        departure = LocalDateTime.now();
        departureTime = new SimpleStringProperty(departure.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }

    /**
     * Calculates the total duration based off of the arrival and departure time.
     * It then rounds up to the nearest hour to get an accurate price.
     */
    private void calculateDuration() {
        double minutes = arrival.until(departure, ChronoUnit.MINUTES);
        double hours = minutes / 60;
        hoursStayed = Math.ceil(hours);
        duration = new SimpleStringProperty(Double.toString(hoursStayed));
    }

    /**
     * Generates a unique ID to be associated with this ticket.
     * The end of the ID is based off of the date.
     * @param ticketNum The current ticket number of the day.
     */
    private void generateID(String ticketNum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String idSuffix = arrival.format(formatter);
        this.id = new SimpleStringProperty(ticketNum + idSuffix);
    }

    /**
     * Returns the payment due based on the hourly price of the space.
     * @return
     */
    @Override
    public double calculatePay() {
        payment = hoursStayed * TicketSystem.getInstance().getPRICE_PER_HOUR();
        return payment;
    }

    /**
     * Returns the payment due based on the hourly price of the space plus any added tax.
     * Also sets totalPay.
     * @return
     */
    @Override
    public double calculateTaxPay() {
        double taxToAdd = payment * TicketSystem.getInstance().getTAX_PERCENT();
        totalDue = payment + taxToAdd;

        DecimalFormat df = new DecimalFormat("#.00");
        totalPay = new SimpleStringProperty("$" + df.format(totalDue));

        return totalDue;
    }
}
