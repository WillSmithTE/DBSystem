package ses1grp6.DBSystemBE.model;

import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.Basic;

@Entity
public class Donor extends User{

    @Basic
    @Column(name="created_at", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

    public Donor(RegistrationRequest registrationRequest) {
        super(registrationRequest);
    }

    public Donor() {
    }
}
