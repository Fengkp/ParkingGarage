package system;

import system.Invoice;

public class Ticket extends Invoice {
    private String customer;
    private String licensePlate;
    private String id;
    private boolean isActive;


    public Ticket() {}

    public void setActive(boolean newActive) {
        super.isActive = newActive;
    }

    public void setArrival() {}

    public void setDeparture() {}

    private void generateID() {}
}
