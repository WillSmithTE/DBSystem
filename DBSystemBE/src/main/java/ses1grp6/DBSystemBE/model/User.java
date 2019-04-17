package ses1grp6.DBSystemBE.model;

/**
 * Created by Will Smith on 15/3/19.
 */

@javax.persistence.Entity
public class User extends Entity {

    public User(String name, String email, String password, int locationId) {
        super(name, email, password, locationId);
    }

    public User(RegistrationRequest registrationRequest) {
        this(
                registrationRequest.getName(),
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                registrationRequest.getLocation()
        );
    }

    public User() {}
}
