package ses1grp6.DBSystemBE.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Created by Will Smith on 4/4/19.
 */
 @Entity
public class Charity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int charityID;
    private String charityName;
    private int charitySize;
    private int industryID;
    private int locationID;
    private String contactNumber;
    

    public Charity() {
    }

    public Charity(int charityID, String charityName, int charitySize, int industryID, int locationID, String contactNumber) {
        this.charityID = charityID;
        this.charityName = charityName;
        this.charitySize = charitySize;
        this.industryID = industryID;
        this.locationID = locationID;
        this.contactNumber = contactNumber;
    }

    public int getCharityID() {
        return this.charityID;
    }

    public void setCharityID(int charityID) {
        this.charityID = charityID;
    }

    public String getCharityName() {
        return this.charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public int getCharitySize() {
        return this.charitySize;
    }

    public void setCharitySize(int charitySize) {
        this.charitySize = charitySize;
    }

    public int getIndustryID() {
        return this.industryID;
    }

    public void setIndustryID(int industryID) {
        this.industryID = industryID;
    }

    public int getLocationID() {
        return this.locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }    
    

}
