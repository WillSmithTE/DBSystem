package ses1grp6.dbsystemandroid.charity.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ses1grp6.dbsystemandroid.charity.model.Charity;

public class Listing implements Serializable {

    private int id;
    private Charity charity;
    private String listingTitle;
    private String contactNumber;
    private String listingDescription;
    private String location;
    private Date timestamp;
    private String industry;

    public Listing(int id, Charity charity, String listingTitle, String contactNumber, String listingDescription, String location, Date timestamp, String industry) {
        this.id = id;
        this.charity = charity;
        this.listingTitle = listingTitle;
        this.contactNumber = contactNumber;
        this.listingDescription = listingDescription;
        this.location = location;
        this.timestamp = timestamp;
        this.industry = industry;
    }

    public Listing(JSONObject obj) throws JSONException, ParseException {
        this.listingTitle = obj.getString("listingTitle");
        this.listingDescription = obj.getString("listingDescription");
        this.location = obj.getString("location");
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(obj.getString("timestamp"));
        this.id = obj.getInt("id");
        this.charity = new Charity(obj.getJSONObject("charity"));
        this.contactNumber = obj.getString("contactNumber");
        this.industry = obj.getString("industry");
    }

    public int getId() {
        return id;
    }

    public Charity getCharity() {
        return charity;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public String getLocation() {
        return location;
    }

    public String getIndustry() {
        return industry;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTimestampString() {
        return new SimpleDateFormat("dd MM yyyy").format(timestamp);
    }
}
