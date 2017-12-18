package garage.model;

import garage.model.ticket.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The InvoiceSystem class stores and handles any new invoices from previously closed tickets.
 * There can only be one instance of an InvoiceSystem object at any given point once instantiated.
 * The object can be accessed once imported using the getInstance() method.
 *
 * @author Parra
 * @version 1.0 Dec. 18, 2017.
 */
public final class InvoiceSystem implements Serializable {
    private static InvoiceSystem invoiceSys = new InvoiceSystem();

    private Map<String, Invoice> storedInvoices;

    /**
     * Constructs and initializes the InvoiceSystem class.
     */
    private InvoiceSystem() {
        storedInvoices = new HashMap<>();
    }

    /**
     * Returns the InvoiceSystem object and allows for access across all other classes.
     * @return
     */
    public static InvoiceSystem getInstance() {
        if (invoiceSys == null)
            invoiceSys = new InvoiceSystem();
        return invoiceSys;
    }

    /**
     * Add a new invoice from an existing ticket.
     * @param invoice Invoice to be added.
     */
    public void addInvoice(Invoice invoice) {
        storedInvoices.put(invoice.getId(), invoice);
    }

    /**
     * Returns an observable list to be used for JavaFX table.
     * @return
     */
    public ObservableList<Invoice> getInvoices() {
        ObservableList<Invoice> invoices = FXCollections.observableArrayList();
        invoices.addAll(storedInvoices.values());

        return invoices;
    }

    /**
     * Allows the Invoice System object and its data to be saved in order to be accessed across multiple sessions.
     */
    public static void save() {
        try {
            FileOutputStream fileOutStream = new FileOutputStream("data/invoicesystem.ser");
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(InvoiceSystem.invoiceSys);
            objectOutStream.flush();
            objectOutStream.close();
            fileOutStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Pulls the Invoice System object and its data from the file.
     * Replaces any current Invoice System object with the new information.
     * @throws ClassNotFoundException
     */
    public static void load() throws ClassNotFoundException {
        try {
            File usersFile = new File("data/invoicesystem.ser");
            if (usersFile.length() == 0)
                System.out.println("Creating New File...");
            else {
                FileInputStream fileInStream = new FileInputStream("data/invoicesystem.ser");
                ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
                InvoiceSystem.invoiceSys = (InvoiceSystem) objectInStream.readObject();
                objectInStream.close();
                fileInStream.close();
            }
        } catch (IOException ioe) {
        }
    }

}
