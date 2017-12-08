package system;

import garage.Lot;
import person.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TicketSystem {
    private Map<String, Ticket> openTickets;
    private Stack<Ticket> closedTickets;
    private String currentTicketNum;
    private Lot mainLot;

    public TicketSystem() {
        this.openTickets = new HashMap<>();
        this.closedTickets = new Stack<>();
        this.currentTicketNum = "0000";
        this.mainLot = new Lot();
    }

    public void openTicket(String firstName, String lastName, String licensePlate) {
        Customer newCustomer = new Customer(firstName, lastName, licensePlate);
        this.currentTicketNum = String.format("%0" + this.currentTicketNum.length() + "d", Integer.parseInt(this.currentTicketNum) + 1);
        Ticket newTicket = new Ticket(newCustomer, this.currentTicketNum);
        this.openTickets.put(newTicket.getId(), newTicket);
        this.mainLot.closeSpace(newCustomer.getVehicle());
    }

    public void closeTicket(String key) {
        if (openTickets.containsKey(key)) {
            this.openTickets.get(key).setDeparture();
            this.openTickets.get(key).setActive(false);
            this.openTickets.get(key).getCustomer().getVehicle().setParked(false);
            this.closedTickets.push(this.openTickets.remove(key));
            this.mainLot.openSpace(closedTickets.peek().getCustomer().getVehicle().getLicensePlate());
        }
    }



}
