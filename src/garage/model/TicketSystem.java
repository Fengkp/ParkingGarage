package garage.model;

import garage.model.garage.Lot;
import garage.model.garage.Space;
import garage.model.person.Customer;
import garage.model.ticket.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The TicketSystem class stores and handles the opening and closing of tickets.
 * There can only be one instance of a TicketSystem object at any given point once instantiated.
 * The object can be accessed once imported using the getInstance() method.
 *
 * @author Feng Parra
 * @version 1.0 Dec. 17, 2017.
 */
public final class TicketSystem implements Serializable {
    private static TicketSystem ticketSys = new TicketSystem();
    private static final long serialVersionUID = 152017L;

    private final double PRICE_PER_HOUR = 15.00;
    private final double TAX_PERCENT = .18;

    private Map<String, Ticket> openTicketsByTicketNum;
    private Map<String, Ticket> openTicketsByLicensePlate;
    private Map<String, Ticket> openTicketsByName;
    private Stack<Ticket> closedTickets;
    private String currentTicketNum;
    private Lot mainLot;

    /**
     * Constructs and initializes the TicketSystem class along with instantiating the Lot class.
     * The class makes three separate maps to reference the ticket object with different keys.
     */
    private TicketSystem() {
        openTicketsByTicketNum = new HashMap<>();
        openTicketsByLicensePlate = new HashMap<>();
        openTicketsByName = new HashMap<>();
        closedTickets = new Stack<>();
        currentTicketNum = "0000";
        mainLot = Lot.getInstance();
    }

    /**
     * Returns the TicketSystem object and allows for access across all other classes.
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
     * @param phoneNum Customers primary contact number.
     */
    public void openTicket(String firstName, String lastName, String licensePlate, String phoneNum) {
        Customer newCustomer = new Customer(firstName, lastName, licensePlate, phoneNum);
        currentTicketNum = String.format("%0" + currentTicketNum.length() + "d", Integer.parseInt(currentTicketNum) + 1);
        mainLot.closeSpace(newCustomer.getVehicle());
        Ticket newTicket = new Ticket(newCustomer, currentTicketNum, newCustomer.getVehicle().getSpaceNum());
        openTicketsByTicketNum.put(newTicket.getId(), newTicket);
        openTicketsByLicensePlate.put(newTicket.getCustomer().getVehicle().getLicensePlate(), newTicket);
        openTicketsByName.put(newTicket.getCustomer().getName(), newTicket);
    }

    /**
     * Closes a ticket based on the tickets ID number.
     * Departure is determined along with the payment amount then passed to the closedTickets Stack.
     * The space assigned to the customers vehicle is pulled and placed back in its appropriate Queue.
     * @param key The ticket numbers ID.
     */
    public void closeTicket(String key) {
        if (openTicketsByTicketNum.containsKey(key)) {
            openTicketsByLicensePlate.remove(openTicketsByTicketNum.get(key).getLicensePlate());
            openTicketsByName.remove(openTicketsByTicketNum.get(key).getCustomerName());
            openTicketsByTicketNum.get(key).closeTicket();
            closedTickets.push(openTicketsByTicketNum.remove(key));
            mainLot.openSpace(closedTickets.peek().getCustomer().getVehicle().getLicensePlate());
        }
    }

    /**
     * Returns amount of available spaces.
     * @return
     */
    public String getSpaceCount() {
        return mainLot.getAvailableSpaces();
    }

    /**
     * Finds the next available space.
     * @return
     */
    public Space nextOpenSpace() {
        return mainLot.nextOpenSpace();
    }

    /**
     * Returns the ticket most recently closed.
     * @return
     */
    public Ticket getClosedTicket() {
        return closedTickets.peek();
    }

    /**
     * Returns the price per hour.
     * @return
     */
    public double getPRICE_PER_HOUR() {
        return PRICE_PER_HOUR;
    }

    /**
     * Returns the tax percentage.
     * @return
     */
    public double getTAX_PERCENT() {
        return TAX_PERCENT;
    }

    /**
     * Returns the ticket if found.
     * @param key The ticket number ID, license plate number or customer name.
     * @return
     */
    public Ticket getTicket(String key) {
        if (openTicketsByTicketNum.containsKey(key))
            return openTicketsByTicketNum.get(key);
        if (openTicketsByLicensePlate.containsKey(key))
            return openTicketsByLicensePlate.get(key);
        if (openTicketsByName.containsKey(key))
            return openTicketsByName.get(key);
        return null;
    }

    /**
     * Returns an observable list to be used for JavaFX table.
     * @return
     */
    public ObservableList<Ticket> getOpenTickets() {
        ObservableList<Ticket> tickets = FXCollections.observableArrayList();
        tickets.addAll(openTicketsByTicketNum.values());

        return tickets;
    }

    /**
     * Find a ticket within one of the maps based on key given.
     * @param key Could be ticket number ID, vehicle license plate number or the name of the customer.
     * @return
     */
    public boolean findTicket(String key) {
        if (openTicketsByTicketNum.containsKey(key))
            return true;
        if (openTicketsByLicensePlate.containsKey(key))
            return true;
        if (openTicketsByName.containsKey(key))
            return true;
        return false;
    }

    /**
     * Allows the Ticket System object and its data to be saved in order to be accessed across multiple sessions.
     */
    public static void save() {
        try {
            FileOutputStream fileOutStream = new FileOutputStream("data/ticketsystem.ser");
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
            File usersFile = new File("data/ticketsystem.ser");
            if (usersFile.length() == 0)
                System.out.println("Creating New File...");
            else {
                FileInputStream fileInStream = new FileInputStream("data/ticketsystem.ser");
                ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
                TicketSystem.ticketSys = (TicketSystem) objectInStream.readObject();
                objectInStream.close();
                fileInStream.close();
            }
        } catch (IOException ioe) {
        }
    }
}
