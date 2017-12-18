package garage.model.ticket;

import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Record implements Serializable {
    protected transient SimpleStringProperty customerName;
    protected transient SimpleStringProperty licensePlate;
    protected transient SimpleStringProperty id;
    protected transient SimpleStringProperty spaceNum;
    protected transient SimpleStringProperty level;
    protected transient SimpleStringProperty phoneNum;
    protected transient SimpleStringProperty arrivalTime;
    protected transient SimpleStringProperty departureTime = new SimpleStringProperty("");
    protected transient SimpleStringProperty totalPay = new SimpleStringProperty("");
    protected transient SimpleStringProperty duration = new SimpleStringProperty("");
    protected transient SimpleStringProperty arrivalDate = new SimpleStringProperty("");;
    protected transient SimpleStringProperty departureDate = new SimpleStringProperty("");
    protected transient SimpleStringProperty year = new SimpleStringProperty("");
    protected transient SimpleStringProperty month = new SimpleStringProperty("");

    public String getCustomerName() {
        return customerName.get();
    }

    public String getLicensePlate() {
        return licensePlate.get();
    }

    public String getId() {
        return id.get();
    }

    public String getSpaceNum() {
        return spaceNum.get();
    }


    public String getLevel() {
        return level.get();
    }

    public String getPhoneNum() {
        return phoneNum.get();
    }

    public String getArrivalTime() {
        return arrivalTime.get();
    }

    public String getDepartureTime() {
        return departureTime.get();
    }

    public String getTotalPay() {
        return totalPay.get();
    }

    public String getDuration() {
        return duration.get();
    }

    public String getArrivalDate() {
        return arrivalDate.get();
    }

    public String getDepartureDate() {
        return departureDate.get();
    }

    public String getYear() {
        return year.get();
    }

    public String getMonth() {
        return month.get();
    }

    /**
     * Allows for the serialization of the String Property values.
     * @param out Data file to be written.
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        out.writeObject(customerName.get());
        out.writeObject(licensePlate.get());
        out.writeObject(id.get());
        out.writeObject(spaceNum.get());
        out.writeObject(level.get());
        out.writeObject(phoneNum.get());
        out.writeObject(arrivalTime.get());
        out.writeObject(departureTime.get());
        out.writeObject(totalPay.get());
        out.writeObject(duration.get());
        out.writeObject(arrivalDate.get());
        out.writeObject(departureDate.get());
        out.writeObject(month.get());
        out.writeObject(year.get());
    }

    /**
     * Deserializes the string values and stores them back into the String Property variables.
     * @param in Data file to be read.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        customerName = new SimpleStringProperty((String)in.readObject());
        licensePlate = new SimpleStringProperty((String)in.readObject());
        id = new SimpleStringProperty((String)in.readObject());
        spaceNum = new SimpleStringProperty((String)in.readObject());
        level = new SimpleStringProperty((String)in.readObject());
        phoneNum = new SimpleStringProperty((String)in.readObject());
        arrivalTime = new SimpleStringProperty((String)in.readObject());
        departureTime = new SimpleStringProperty((String)in.readObject());
        totalPay = new SimpleStringProperty((String)in.readObject());
        duration = new SimpleStringProperty((String)in.readObject());
        arrivalDate = new SimpleStringProperty((String)in.readObject());
        departureDate = new SimpleStringProperty((String)in.readObject());
        month = new SimpleStringProperty((String)in.readObject());
        year = new SimpleStringProperty((String)in.readObject());
    }

    @Override
    public int hashCode() {
        return this.id.get().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this.id.get().equals(o);
    }
}
