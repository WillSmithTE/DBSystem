package ses1grp6.DBSystemBE.model;

import javax.persistence.MappedSuperclass;

/**
 * Created by Will Smith on 3/5/19.
 */

@MappedSuperclass
public abstract class User {
    private String name;
    private String email;
    private String contactNumber;
    private String password;
    private boolean emailConfirmed = false;
//    private String location;

    public User() {
    }

    public void assignRegisterProperties(RegistrationRequest registrationRequest) {
        email = registrationRequest.getEmail();
        name = registrationRequest.getName();
        contactNumber = registrationRequest.getContactNumber();
        password = registrationRequest.getPassword();
    }

    private User(String name, String email, String contactNumber, String password) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
    }

    public abstract Long getId();
//        return null;
//    }

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

//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
}
