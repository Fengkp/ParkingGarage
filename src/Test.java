import system.TicketSystem;

public class Test {

    public static void main(String[] args) {
        TicketSystem sys1 = new TicketSystem();
        sys1.openTicket("Feng", "Parra", "HBX1-231");
        sys1.openTicket("Bob", "Parker", "HBX1-232");
        sys1.openTicket("Joe", "Stalin", "HUX1-232");
        sys1.openTicket("Greg", "Gold", "HCX1-232");
        sys1.openTicket("Feng", "Parr", "HBX1-238");
        sys1.openTicket("Chris", "Red", "HBX2-232");
        sys1.openTicket("Jackie", "Chan", "HBX7-232");
        sys1.openTicket("Chris", "Tucker", "HBX1-235");

        sys1.closeTicket("000520171207");
        System.out.println();




    }
}
