package ses1grp6.dbsystemandroid.donor;

public class Donor {
    String name;
    String email;
    String phone;

    public Donor(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
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
}
