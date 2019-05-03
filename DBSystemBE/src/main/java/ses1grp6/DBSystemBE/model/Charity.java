package ses1grp6.DBSystemBE.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Created by Will Smith on 4/4/19.
 */
 @Entity
public class Charity extends User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long charityId;
    private int charitySize;
    private int industryID;

    public Charity() {
    }

    public Charity(RegistrationRequest registrationRequest) {
        this();
        assignRegisterProperties(registrationRequest);
    }

    public Long getId() {
        return getCharityId();
    }

    public Long getCharityId() {
        return charityId;
    }

    public int getCharitySize() {
        return charitySize;
    }

    public void setCharitySize(int charitySize) {
        this.charitySize = charitySize;
    }

    public int getIndustryID() {
        return industryID;
    }

    public void setIndustryID(int industryID) {
        this.industryID = industryID;
    }
}
