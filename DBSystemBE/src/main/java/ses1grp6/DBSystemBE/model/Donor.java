package ses1grp6.DBSystemBE.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Donor extends User{

    @Basic
    @Column(name="created_at", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = new Date();
        }
    }

    public Donor(RegistrationRequest registrationRequest) {
        super(registrationRequest);
    }

    public Donor() {
    }
}
