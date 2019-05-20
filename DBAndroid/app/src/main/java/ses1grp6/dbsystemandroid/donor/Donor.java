package ses1grp6.dbsystemandroid.donor;

import java.io.Serializable;

public class Donor implements Serializable {
    String name;
    String email;
    String phone;
    int id;

    public Donor(int id, String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() { return id; }
}
