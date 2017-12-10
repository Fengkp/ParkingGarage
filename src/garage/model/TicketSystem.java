package garage.model;

import garage.model.garage.Lot;
import garage.model.person.Customer;
import garage.model.ticket.Ticket;

import java.util.*;

public final class TicketSystem {
    private static TicketSystem ticketSys = new TicketSystem();
    public Map<String, Ticket> openTickets;
    private Stack<Ticket> closedTickets;
    private String currentTicketNum;
    private Lot mainLot;

    private TicketSystem() {
        openTickets = new HashMap<>();
        closedTickets = new Stack<>();
        currentTicketNum = "0000";
        mainLot = Lot.getInstance();
    }

    public static TicketSystem getInstance() {
        if (ticketSys == null)
            ticketSys = new TicketSystem();
        return ticketSys;
    }

    public void openTicket(String firstName, String lastName, String licensePlate) {
        Customer newCustomer = new Customer(firstName, lastName, licensePlate);
        currentTicketNum = String.format("%0" + currentTicketNum.length() + "d", Integer.parseInt(currentTicketNum) + 1);
        mainLot.closeSpace(newCustomer.getVehicle());
        Ticket newTicket = new Ticket(newCustomer, currentTicketNum, newCustomer.getVehicle().getSpaceNum());
        openTickets.put(newTicket.getId(), newTicket);
    }

    public void closeTicket(String key) {
        if (openTickets.containsKey(key)) {
            openTickets.get(key).setDeparture();
            openTickets.get(key).setActive(false);
            openTickets.get(key).getCustomer().getVehicle().setParked(false);
            closedTickets.push(openTickets.remove(key));
            mainLot.openSpace(closedTickets.peek().getCustomer().getVehicle().getLicensePlate());
        }
    }

    public boolean findTicket(String key) {
        if (openTickets.containsKey(key))
            return true;
        return false;
    }

    public Ticket getTicket(String key) {
        if (findTicket(key))
            return openTickets.get(key);
        return null;
    }

}
