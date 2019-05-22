package ses1grp6.DBSystemBE.model;

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

    public Charity(RegistrationRequest registrationRequest) {
        super(registrationRequest);
    }

    public Charity() {
    }

    public Charity(int charitySize) {
        this.charitySize = charitySize;
    }

    public int getCharitySize() {
        return this.charitySize;
    }

    public void setCharitySize(int charitySize) {
        this.charitySize = charitySize;
    }

    public Charity charitySize(int charitySize) {
        this.charitySize = charitySize;
        return this;
    }
}
