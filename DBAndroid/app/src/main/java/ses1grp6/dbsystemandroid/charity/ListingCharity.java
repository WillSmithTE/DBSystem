package ses1grp6.dbsystemandroid.charity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ListingCharity implements Serializable {
    int id;
    Charity charity;
    String listingTitle;
    String listingDescription;
    String location;
    String timestamp;

    public ListingCharity(JSONObject obj) {
        try {
            listingTitle = obj.getString("listingTitle");
            listingDescription = obj.getString("listingDescription");
            location = obj.getString("location");
            timestamp = obj.getString("timestamp");
            id = obj.getInt("id");
            charity = new Charity(obj.getJSONObject("charity"));

            System.out.println("listingTitle " + listingTitle);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

    }

    public int getId() { return id; }

    public Charity getCharity() { return charity; }

    public String getListingTitle() {
        return listingTitle;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public String getLocation() {
        return location;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
