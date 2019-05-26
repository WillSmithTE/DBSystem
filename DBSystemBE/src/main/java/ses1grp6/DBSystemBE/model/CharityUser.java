package ses1grp6.DBSystemBE.model;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import java.util.Objects;

@Entity
public class CharityUser {
    @Id
    @Column(name="charity_user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int charityUserID;
    @ManyToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="contact_number")
    private String contactNumber;
    private String password;
    private String location;
    @Basic
    @Column(name="created_at", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

    public CharityUser() {
    }

    public CharityUser(int charityUserID, Charity charity, String firstName, String lastName, String contactNumber, String password, String location) {
        this.charityUserID = charityUserID;
        this.charity = charity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.password = password;
        this.location = location;
    }

    public int getCharityUserID() {
        return this.charityUserID;
    }

    public void setCharityUserID(int charityUserID) {
        this.charityUserID = charityUserID;
    }

    public Charity getCharity() {
        return this.charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
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

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CharityUser charityUserID(int charityUserID) {
        this.charityUserID = charityUserID;
        return this;
    }

    public CharityUser charity(Charity charity) {
        this.charity = charity;
        return this;
    }

    public CharityUser firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CharityUser lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CharityUser contactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public CharityUser password(String password) {
        this.password = password;
        return this;
    }

    public CharityUser location(String location) {
        this.location = location;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CharityUser)) {
            return false;
        }
        CharityUser charityUser = (CharityUser) o;
        return charityUserID == charityUser.charityUserID && Objects.equals(charity, charityUser.charity) && Objects.equals(firstName, charityUser.firstName) && Objects.equals(lastName, charityUser.lastName) && Objects.equals(contactNumber, charityUser.contactNumber) && Objects.equals(password, charityUser.password) && Objects.equals(location, charityUser.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(charityUserID, charity, firstName, lastName, contactNumber, password, location);
    }

    @Override
    public String toString() {
        return "{" +
            " charityUserID='" + getCharityUserID() + "'" +
            ", charity='" + getCharity() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", contactNumber='" + getContactNumber() + "'" +
            ", password='" + getPassword() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }
}