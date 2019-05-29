package ses1grp6.dbsystemandroid.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application implements Parcelable {

    private final static String INTENT_NAME = "applicationModel";
    private static final String DONOR = "donor";
    private static final String CHARITY = "charity";
    private static final String COVER_LETTER = "coverLetter";
    private static final String CONTACT_NUMBER = "contactNumber";
    private static final String INDUSTRY = "industry";
    private static final String TIMESTAMP = "timestamp";
    private static final String ID = "id";
    private int id;
    private Donor donor;
    private Charity charity;
    private String coverLetter;
    private String contactNumber;
    private Date timestamp;
    private String industry;

    public Application(int id, Donor donor, Charity charity) {
        this.id = id;
        this.donor = donor;
        this.charity = charity;
    }

    public Application(JSONObject obj) throws ParseException, JSONException {
        this.id = obj.getInt(ID);
        this.donor = new Donor(obj.getJSONObject(DONOR));
        this.charity = new Charity(obj.getJSONObject(CHARITY));
        this.coverLetter = obj.getString(COVER_LETTER);
        this.contactNumber = obj.getString(CONTACT_NUMBER);
        setTimestamp(obj.getString(TIMESTAMP));
        this.industry = obj.getString(INDUSTRY);
    }

    public void putToIntent(Intent intent) {
        intent.putExtra(INTENT_NAME, this);
    }

    public static Application getFromIntent(Intent intent) {
        return intent.getParcelableExtra(INTENT_NAME);
    }

    public boolean hasCoverLetter() {
        return coverLetter != null;
    }

    public boolean hasContactNumber() {
        return contactNumber != null;
    }

    public boolean hasTimestamp() {
        return timestamp != null;
    }

    public boolean hasIndustry() {
        return industry != null;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestamp(String timestampString) throws ParseException {
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(timestampString);;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Donor getDonor() {
        return donor;
    }

    public Charity getCharity() {
        return charity;
    }

    public int getId() {
        return id;
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

    public String getFormattedTimestamp() {
        return new SimpleDateFormat("dd MM yyyy").format(timestamp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.donor, flags);
        dest.writeParcelable(this.charity, flags);
        dest.writeString(this.coverLetter);
        dest.writeString(this.contactNumber);
        dest.writeLong(this.timestamp != null ? this.timestamp.getTime() : -1);
        dest.writeString(this.industry);
    }

    protected Application(Parcel in) {
        this.id = in.readInt();
        this.donor = (Donor) in.readSerializable();
        this.charity = in.readParcelable(Charity.class.getClassLoader());
        this.coverLetter = in.readString();
        this.contactNumber = in.readString();
        long tmpTimestamp = in.readLong();
        this.timestamp = tmpTimestamp == -1 ? null : new Date(tmpTimestamp);
        this.industry = in.readString();
    }

    public static final Parcelable.Creator<Application> CREATOR = new Parcelable.Creator<Application>() {
        @Override
        public Application createFromParcel(Parcel source) {
            return new Application(source);
        }

        @Override
        public Application[] newArray(int size) {
            return new Application[size];
        }
    };
}
