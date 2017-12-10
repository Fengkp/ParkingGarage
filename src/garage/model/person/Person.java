package garage.model.person;

public abstract class Person {
    protected String firstName;
    protected String lastName;

    public String toString() {
        return firstName + " " + lastName;
    }
}
