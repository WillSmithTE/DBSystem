package ses1grp6.DBSystemBE.model;

import java.util.Date;
import javax.persistence.*;
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

    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = new Date();
        }
    }

    public Charity(RegistrationRequest registrationRequest) {
        super(registrationRequest);
    }

    public Charity() {
    }

    public Charity(int id) {
        super(id);
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

    @Override
    public String toString() {
        return "Charity{" +
                "charitySize=" + charitySize +
                ", timestamp=" + timestamp +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Charity charity = (Charity) o;

        if (charitySize != charity.charitySize) return false;
        return timestamp != null ? timestamp.equals(charity.timestamp) : charity.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = charitySize;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
