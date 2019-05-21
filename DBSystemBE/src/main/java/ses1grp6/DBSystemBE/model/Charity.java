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

    // @Id
    // @Column(name="charity_id")
    // // @GeneratedValue(strategy = GenerationType.AUTO)
    // private Long charityID;

    private int charitySize;
    
    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    public Charity(RegistrationRequest registrationRequest) {
        super(registrationRequest);
    }

    public Charity() {
    }


    public Charity(Long charityID, int charitySize, Industry industry) {
        // this.charityID = super.id;
        this.charitySize = charitySize;
        this.industry = industry;
    }

    // public Long getCharityID() {
    //     return this.charityID;
    // }

    // public void setCharityID(Long charityID) {
    //     this.charityID = charityID;
    // }

    public int getCharitySize() {
        return this.charitySize;
    }

    public void setCharitySize(int charitySize) {
        this.charitySize = charitySize;
    }

    public Industry getIndustry() {
        return this.industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    // public Charity charityID(Long charityID) {
    //     this.charityID = charityID;
    //     return this;
    // }

    public Charity charitySize(int charitySize) {
        this.charitySize = charitySize;
        return this;
    }

    public Charity industry(Industry industry) {
        this.industry = industry;
        return this;
    }

    // @Override
    // public boolean equals(Object o) {
    //     if (o == this)
    //         return true;
    //     if (!(o instanceof Charity)) {
    //         return false;
    //     }
    //     Charity charity = (Charity) o;
    //     return Objects.equals(charityID, charity.charityID) && charitySize == charity.charitySize && Objects.equals(industry, charity.industry);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(charityID, charitySize, industry);
    // }

    // @Override
    // public String toString() {
    //     return "{" +
    //         " charityID='" + getCharityID() + "'" +
    //         ", charitySize='" + getCharitySize() + "'" +
    //         ", industry='" + getIndustry() + "'" +
    //         "}";
    // }


}
