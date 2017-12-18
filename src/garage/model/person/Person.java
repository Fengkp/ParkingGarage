package garage.model.person;

import java.io.Serializable;

/**
 * The person abstract class serves as a template for all future classes associated with the person type.
 *
 * @author Feng Parra
 * @version 1.0 Dec. 17, 2017.
 */
public abstract class Person implements Serializable {
    protected String firstName;
    protected String lastName;

    /**
     * Returns the person's full name as a String object.
     * @return
     */
    public String toString() {
        return firstName + " " + lastName;
    }
}
