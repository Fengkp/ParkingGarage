package system;

import java.util.HashMap;
import java.util.Map;

public class App {
    private Map<Ticket, String> tickets;
    private String currentTicketNum;

    public App() {
        tickets = new HashMap<>();
        currentTicketNum = "0000";
    }

    public void openTicket(String firstName, String lastName, String licensePlate) {
        this.currentTicketNum = String.format("%0" + currentTicketNum.length() + "d", Integer.parseInt(currentTicketNum) + 1);
        Ticket newTicket = new Ticket(firstName, lastName, licensePlate, currentTicketNum);
        tickets.put(newTicket, newTicket.getId());
    }



}
