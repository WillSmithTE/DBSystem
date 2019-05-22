package ses1grp6.DBSystemBE.model;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import java.util.Objects;
/**
 * Created by Will Smith on 4/4/19.
 */

@Entity
public class Charity extends User{
    
    @Column(name="charity_size")
    private int charitySize;

    @Basic
    @Column(name="created_at", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

    public Charity(RegistrationRequest registrationRequest) {
        super(registrationRequest);
    }

    public Charity() {
    }


    public Charity(int charitySize, java.util.Date timestamp) {
        this.charitySize = charitySize;
        this.timestamp = timestamp;
    }

    public int getCharitySize() {
        return this.charitySize;
    }

    public void setCharitySize(int charitySize) {
        this.charitySize = charitySize;
    }

    public java.util.Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }

    public Charity charitySize(int charitySize) {
        this.charitySize = charitySize;
        return this;
    }

    public Charity timestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
