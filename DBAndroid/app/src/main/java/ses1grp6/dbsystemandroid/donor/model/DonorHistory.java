package ses1grp6.dbsystemandroid.donor.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DonorHistory {

    public final String title;
    public final Date date;
    public final String charityName;
    public final String address;
    public final String description;
    public final String industry;

    public DonorHistory(String title, Date date, String charityName, String address, String description, String industry) {
        this.title = title;
        this.date = date;
        this.charityName = charityName;
        this.address = address;
        this.description = description;
        this.industry = industry;

    }

    public DonorHistory(JSONObject jsonObject) throws JSONException, ParseException {
        this.title = jsonObject.getJSONObject("charityListing").getString("listingTitle");
        this.date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(jsonObject.getString("timestamp"));
        this.charityName = jsonObject.getJSONObject("charity").getString("name");
        this.address = jsonObject.getString("location");
        this.description = jsonObject.getString("coverLetter");
        this.industry = jsonObject.getJSONObject("industry").getString("industryName");
    }
}
