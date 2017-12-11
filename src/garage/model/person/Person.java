package garage.model.person;

import java.io.Serializable;

public abstract class Person implements Serializable {
    protected String firstName;
    protected String lastName;

    public String toString() {
        return firstName + " " + lastName;
    }
}
