package ses1grp6.DBSystemBE.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


// @javax.persistence.Entity
@Entity
public class Donors {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String password;
    private int locationId;
    private int emailConfirmed;

    public Donors(int userId, String firstName, String lastName, String email, String contactNumber, String password, int locationId, int emailConfirmed) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.locationId = locationId;
        this.emailConfirmed = emailConfirmed;
    }

    public Donors(RegistrationRequest registrationRequest) {
        this(
                registrationRequest.getUserId(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                registrationRequest.getContactNumber(),
                registrationRequest.getPassword(),
                registrationRequest.getLocationId(),
                registrationRequest.getEmailConfirmed()
        );
    }

    public Donors() {}


    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void SetLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public int getLocationId() {
        return locationId;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    
    public int getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(int emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }
}
