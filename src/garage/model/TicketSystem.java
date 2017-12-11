package garage.model;

import garage.model.garage.Lot;
import garage.model.person.Customer;
import garage.model.ticket.Ticket;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public final class TicketSystem implements Serializable {
    private static TicketSystem ticketSys = new TicketSystem();
    private static final long serialVersionUID = 152017L;

    private Map<String, Ticket> openTickets;
    private Stack<Ticket> closedTickets;
    private String currentTicketNum;
    private Lot mainLot;
    private final double PRICE_PER_HOUR = 15.00;

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
            openTickets.get(key).calculatePayment(PRICE_PER_HOUR);
            openTickets.get(key).getCustomer().getVehicle().setParked(false);
            closedTickets.push(openTickets.remove(key));
            mainLot.openSpace(closedTickets.peek().getCustomer().getVehicle().getLicensePlate());
        }
    }

    public Ticket getClosedTicket() {
        return closedTickets.peek();
    }

    public double getPRICE_PER_HOUR() {
        return PRICE_PER_HOUR;
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

    public static void save() {
        try {
            FileOutputStream fileOutStream = new FileOutputStream("data" + File.separator + "data.ser");
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(TicketSystem.ticketSys);
            objectOutStream.flush();
            objectOutStream.close();
            fileOutStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void load() throws ClassNotFoundException {
        try {
            File usersFile = new File("data" + File.separator + "data.ser");
            if (usersFile.length() == 0)
                System.out.println("Creating New File...");
            else {
                FileInputStream fileInStream = new FileInputStream("data" + File.separator + "data.ser");
                ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
                TicketSystem.ticketSys = (TicketSystem) objectInStream.readObject();
                objectInStream.close();
                fileInStream.close();
            }
        } catch (IOException ioe) {
        }
    }
}
