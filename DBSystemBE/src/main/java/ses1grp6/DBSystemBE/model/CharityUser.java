package ses1grp6.DBSystemBE.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CharityUser {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int charityUserID;
    private int charityID;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String password;
    private int locationID;


    public CharityUser() {
    }

    public CharityUser(int charityUserID, int charityID, String firstName, String lastName, String contactNumber, String password, int locationID) {
        this.charityUserID = charityUserID;
        this.charityID = charityID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.password = password;
        this.locationID = locationID;
    }

    public int getCharityUserID() {
        return this.charityUserID;
    }

    public void setCharityUserID(int charityUserID) {
        this.charityUserID = charityUserID;
    }

    public int getCharityID() {
        return this.charityID;
    }

    public void setCharityID(int charityID) {
        this.charityID = charityID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLocationID() {
        return this.locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }



}