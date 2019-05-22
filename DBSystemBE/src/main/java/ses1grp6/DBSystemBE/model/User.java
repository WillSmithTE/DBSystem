package ses1grp6.DBSystemBE.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Created by Will Smith on 3/5/19.
 */

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @NotNull
    private String email;
    private String contactNumber;
    @NotNull
    private String password;
    private boolean emailConfirmed = false;

    public User(String name, String email, String contactNumber, String password) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
    }
    public User(Integer id, String name, String email, String contactNumber, String password, boolean emailConfirmed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.emailConfirmed = emailConfirmed;
    }
    
    public User() {
    }

    public User(RegistrationRequest registrationRequest) {
        this(registrationRequest.getName(), registrationRequest.getEmail(), registrationRequest.getContactNumber(), registrationRequest.getPassword());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void confirmEmail() {
        this.emailConfirmed = true;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEmailConfirmed() {
        return this.emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

}
