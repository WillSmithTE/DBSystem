package ses1grp6.DBSystemBE.model;

import javax.persistence.*;
import java.util.Date;
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
    private java.util.Date createdAt;
    
    @org.hibernate.annotations.Type( type = "text" )
    private String charity_description;
    
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
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


    public Charity(int charitySize, java.util.Date createdAt, String charity_description) {
        this.charitySize = charitySize;
        this.createdAt = createdAt;
        this.charity_description = charity_description;
    }

    public int getCharitySize() {
        return this.charitySize;
    }

    public void setCharitySize(int charitySize) {
        this.charitySize = charitySize;
    }

    public java.util.Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCharity_description() {
        return this.charity_description;
    }

    public void setCharity_description(String charity_description) {
        this.charity_description = charity_description;
    }

    public Charity charitySize(int charitySize) {
        this.charitySize = charitySize;
        return this;
    }

    public Charity createdAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Charity charity_description(String charity_description) {
        this.charity_description = charity_description;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " charitySize='" + getCharitySize() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", charity_description='" + getCharity_description() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Charity charity = (Charity) o;

        if (charitySize != charity.charitySize) return false;
        return createdAt != null ? createdAt.equals(charity.createdAt) : charity.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = charitySize;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
