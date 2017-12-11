package garage.model;

import garage.model.garage.Lot;
import garage.model.person.Customer;
import garage.model.ticket.Ticket;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The Ticket System class stores and handles the opening and closing of tickets.
 * There can only be one instance of a Ticket System object at any given point once instantiated.
 * The object can be accessed once imported using the getInstance() method.
 *
 * @author Feng Parra
 * @version 1.0 Dec. 11, 2017.
 */
public final class TicketSystem implements Serializable {
    private static TicketSystem ticketSys = new TicketSystem();
    private static final long serialVersionUID = 152017L;

    private Map<String, Ticket> openTickets;
    private Stack<Ticket> closedTickets;
    private String currentTicketNum;
    private Lot mainLot;
    private final double PRICE_PER_HOUR = 15.00;

    /**
     * Constructs and initializes the Ticket System class along with instantiating the Lot class.
     */
    private TicketSystem() {
        openTickets = new HashMap<>();
        closedTickets = new Stack<>();
        currentTicketNum = "0000";
        mainLot = Lot.getInstance();
    }

    /**
     * Returns the Ticket System object and allows for access across all other classes.
     * @return
     */
    public static TicketSystem getInstance() {
        if (ticketSys == null)
            ticketSys = new TicketSystem();
        return ticketSys;
    }

    /**
     * Creates a new ticket using the customers information.
     * A new Customer object is created and stored within the Ticket object.
     * An open space is retrieved then assigned with the customers vehicle.
     * The ticket is then saved in the openTickets Map.
     * @param firstName Customers first name.
     * @param lastName Customers last name.
     * @param licensePlate Vehicles license plate number associated with the customer.
     */
    public void openTicket(String firstName, String lastName, String licensePlate) {
        Customer newCustomer = new Customer(firstName, lastName, licensePlate);
        currentTicketNum = String.format("%0" + currentTicketNum.length() + "d", Integer.parseInt(currentTicketNum) + 1);
        mainLot.closeSpace(newCustomer.getVehicle());
        Ticket newTicket = new Ticket(newCustomer, currentTicketNum, newCustomer.getVehicle().getSpaceNum());
        openTickets.put(newTicket.getId(), newTicket);
    }

    /**
     * Closes a ticket based on the tickets ID number.
     * Departure is determined along with the payment amount then passed to the closedTickets Stack.
     * The space assigned to the customers vehicle is pulled and placed back in its appropriate Queue.
     * @param key The ticket numbers ID.
     */
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

    /**
     * Returns the ticket most recently closed.
     * @return
     */
    public Ticket getClosedTicket() {
        return closedTickets.peek();
    }

    public double getPRICE_PER_HOUR() {
        return PRICE_PER_HOUR;
    }

    /**
     * Determines if the ticket exists within the openTickets Map.
     * @param key The ticket numbers ID.
     * @return
     */
    public boolean findTicket(String key) {
        if (openTickets.containsKey(key))
            return true;
        return false;
    }

    /**
     * Returns the ticket if found.
     * @param key The ticket numbers ID.
     * @return
     */
    public Ticket getTicket(String key) {
        if (findTicket(key))
            return openTickets.get(key);
        return null;
    }

    /**
     * Allows the Ticket System object and its data to be saved in order to be accessed across multiple sessions.
     */
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

    /**
     * Pulls the Ticket System object and its data from the file.
     * Replaces any current Ticket System object with the new information.
     * @throws ClassNotFoundException
     */
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
