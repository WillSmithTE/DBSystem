package ses1grp6.DBSystemBE.model;

/**
 * Created by Will Smith on 3/5/19.
 */
public class RegistrationRequest {
    private String email;
    private String name;
    private String contactNumber;
    private String password;
    private boolean isCharity;

    public RegistrationRequest(String email, String name, String contactNumber, String password, boolean isCharity) {
        this.email = email;
        this.name = name;
        this.contactNumber = contactNumber;
        this.password = password;
        this.isCharity = isCharity;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCharity() {
        return isCharity;
    }

}
