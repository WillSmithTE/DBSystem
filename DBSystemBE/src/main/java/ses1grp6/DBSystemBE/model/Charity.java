package ses1grp6.DBSystemBE.model;

import javax.persistence.Entity;
/**
 * Created by Will Smith on 4/4/19.
 */
 @Entity
public class Charity extends User {

    private int charitySize;
    private int industryID;

    public Charity(RegistrationRequest registrationRequest) {
        super(registrationRequest);
    }

    public Charity() {
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
