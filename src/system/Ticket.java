package system;

import system.Invoice;

public class Ticket extends Invoice {

    public Ticket() {}

    public void setActive(boolean newActive) {
        super.isActive = newActive;
    }

    public void setArrival() {}

    public void setDeparture() {}

    private void generateID() {}
}
