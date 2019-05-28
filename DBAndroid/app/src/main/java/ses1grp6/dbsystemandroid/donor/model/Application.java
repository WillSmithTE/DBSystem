package ses1grp6.dbsystemandroid.donor.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ses1grp6.dbsystemandroid.charity.model.Charity;

public class Application {

    private String title;
    private Donor donor;
    private Charity charity;
    private String coverLetter;
    private String contactNumber;
    private Date timestamp;
    private String industry;

    public Application(String title, Donor donor, Charity charity, String coverLetter, String contactNumber, Date timestamp, String industry) {
        this.title = title;
        this.donor = donor;
        this.charity = charity;
        this.coverLetter = coverLetter;
        this.contactNumber = contactNumber;
        this.timestamp = timestamp;
        this.industry = industry;
    }

    public Application(JSONObject obj) throws ParseException, JSONException {
        this.title = obj.getString("title");
        this.donor = new Donor(obj.getJSONObject("donor"));
        this.charity = new Charity(obj.getJSONObject("charity"));
        this.coverLetter = obj.getString("coverLetter");
        this.contactNumber = obj.getString("contactNumber");
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(obj.getString("timestamp"));
        this.industry = obj.getString("industry");
    }

    public String getTitle() {
        return title;
    }

    public Donor getDonor() {
        return donor;
    }

    public Charity getCharity() {
        return charity;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public String getContactNumber() {
        return contactNumber;
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
