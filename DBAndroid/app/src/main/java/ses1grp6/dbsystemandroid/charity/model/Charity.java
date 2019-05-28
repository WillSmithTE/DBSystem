package ses1grp6.dbsystemandroid.charity.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Charity {

    private int id;
    private String name;
    private String email;
    private String contactNumber;
    private Date timestamp;

    public Charity(JSONObject obj) throws JSONException, ParseException {
        this.id = obj.getInt("id");
        this.name = obj.getString("name");
        this.email = obj.getString("email");
        this.contactNumber = obj.getString("contactNumber");
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(obj.getString("timestamp"));
    }

    public int getId() {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTimestampString() {
        return new SimpleDateFormat("dd MM yyyy").format(timestamp);
    }
}
