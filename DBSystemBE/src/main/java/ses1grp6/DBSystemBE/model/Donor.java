package ses1grp6.DBSystemBE.model;

import javax.persistence.Entity;

@Entity
public class Donor extends User {


    public Donor(RegistrationRequest registrationRequest) {
        super(registrationRequest);
    }


    public Donor() {
    }
}
