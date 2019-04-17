package ses1grp6.DBSystemBE.model;

/**
 * Created by Will Smith on 15/3/19.
 */

@javax.persistence.Entity
public class User extends Entity {

    public User(String name, String email, String password, int location) {
        super(name, email, password, location);
    }

    public User(RegistrationRequest registrationRequest) {
        this(
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                registrationRequest.getLocation()
        );
    }

    public User() {}
}
