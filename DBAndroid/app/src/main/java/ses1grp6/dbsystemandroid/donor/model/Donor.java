package ses1grp6.dbsystemandroid.donor.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Donor implements Serializable {

    private String name;
    private String email;
    private String contactNumber;
    private int id;
    private Date timestamp;

    public Donor(int id, String name, String email, String contactNumber){
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.id = id;
        this.timestamp = new Date(); // Sets the current time to be the current time when this object is created.
    }

    public Donor(String name, String email, String contactNumber, int id, Date timestamp) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.id = id;
        this.timestamp = timestamp;
    }

    public Donor(JSONObject jsonObject) throws JSONException, ParseException {
        this.name = jsonObject.getString("name");
        this.email = jsonObject.getString("email");
        this.contactNumber = jsonObject.getString("contactNumber");
        this.id = jsonObject.getInt("id");
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(jsonObject.getString(""));
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

    public int getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTimestampString() {
        return new SimpleDateFormat("dd MM yyyy").format(timestamp);
    }
}
