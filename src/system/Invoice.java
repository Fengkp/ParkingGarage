package system;

import java.time.chrono.ChronoLocalDate;

public abstract class Invoice {
    private String id;
    private String name;
    private String licensePlate;
    private String spaceNum;
    private ChronoLocalDate arrival;
    private ChronoLocalDate departure;
    protected boolean isActive;
    private double cost;

    public Invoice() {}

//    public double calculatePayment() {}
}
