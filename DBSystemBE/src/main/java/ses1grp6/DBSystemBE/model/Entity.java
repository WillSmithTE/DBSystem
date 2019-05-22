package ses1grp6.DBSystemBE.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Entity {
    @Id
    @Column(name="user_id", unique = true, nullable = false)
    private int userId;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    private String email;
    
    @Column(name="contact_number")
    private String contactNumber;
    private String password;
    private int locationId;
    
    @Column(name="email_confirmed")
    private int emailConfirmed;


    public Entity() {}

    public Entity(int userId, String firstName, String lastName, String email, String contactNumber, String password, int locationId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.locationId = locationId;
    }


    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public int getLocationId() {
        return locationId;
    }
}
