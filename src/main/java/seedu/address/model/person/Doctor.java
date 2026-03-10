package seedu.address.model.person;

import java.util.Set;


public class Doctor extends Person {
    public Doctor(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }

    // Override toString() to reflect the name as Dr. ...
}
