package seedu.address.model.person;

/**
 * Represents a patient in the address book.
 * Extends {@code Person} to support the new 'addpat' command.
 */
public class Patient extends Person {

    public Patient(Name name, Phone phone, Email email, Address address) {
        super(name, phone, email, address);
    }

    @Override
    public String toString() {
        return "Patient: " + super.toString();
    }

}
