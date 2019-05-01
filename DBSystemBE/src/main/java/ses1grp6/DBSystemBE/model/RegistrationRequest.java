package ses1grp6.DBSystemBE.model;

/**
 * Created by Will Smith on 4/4/19.
 */

public class RegistrationRequest {
    private int userId;
    private String email;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String password;
    private int locationId;
    private int emailConfirmed;

    public RegistrationRequest(int userId, String email, String firstName, String lastName, String contactNumber, String password, int locationId, int emailConfirmed) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.password = password;
        this.locationId = locationId;
        this.emailConfirmed = emailConfirmed;
    }

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