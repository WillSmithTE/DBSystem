package ses1grp6.dbsystemandroid.charity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CharityHistory {

    public final String title;
    public final Date date;
    public final String address;
    public final String description;
    public final String industry;

    public CharityHistory(String title, Date date, String address, String description, String industry) {
        this.title = title;
        this.date = date;
        this.address = address;
        this.description = description;
        this.industry = industry;
    }

    public CharityHistory(JSONObject jsonObject) throws JSONException, ParseException {
        this.title = jsonObject.getString("listingTitle");
        this.description = jsonObject.getString("listingDescription");
        this.address = jsonObject.getString("location");
        this.date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(jsonObject.getString("timestamp"));
        this.industry = jsonObject.getJSONObject("industry").getString("industryName");
    }
}
