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
    private static final String CREATED_AT = "createdAt";
    private static final String ID = "applicationID";
    private static final String LISTING = "charityListing";
    public static final String ACCEPTED = "accepted";
    private int id;
    private Donor donor;
    private Charity charity;
    private Listing listing;
    private String coverLetter;
    private String contactNumber;
    private Date createdAt;
    private Industry industry;
    private int accepted;

    public Application(int id, Donor donor, Charity charity) {
        this.id = id;
        this.donor = donor;
        this.charity = charity;
    }

    public Application(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt(ID);
        this.donor = new Donor(jsonObject.getJSONObject(DONOR));
        this.charity = new Charity(jsonObject.getJSONObject(CHARITY));
        this.listing = new Listing(jsonObject.getJSONObject(LISTING));
        this.coverLetter = jsonObject.getString(COVER_LETTER);
        this.accepted = jsonObject.getInt(ACCEPTED);
        if (checkNull(jsonObject, CONTACT_NUMBER)) this.contactNumber = jsonObject.getString(CONTACT_NUMBER);
        if (checkNull(jsonObject, CREATED_AT)) setCreatedAt(jsonObject.getString(CREATED_AT));
        if (checkNull(jsonObject, INDUSTRY)) this.industry = new Industry(jsonObject.getJSONObject(INDUSTRY));
    }

    public boolean checkNull(JSONObject jsonObject, String key) throws JSONException{
        return jsonObject.has(key) && !jsonObject.getString(key).equals("null") && !jsonObject.getString(key).equals("") && !jsonObject.isNull(key);
    }

    public void putToIntent(Intent intent) {
        intent.putExtra(INTENT_NAME, this);
    }

    public static Application getFromIntent(Intent intent) {
        return intent.getParcelableExtra(INTENT_NAME);
    }

    public boolean hasListing() {
        return listing != null;
    }

    public boolean isAccepted() {
        return accepted == 1;
    }

    public boolean isRejected() {
        return accepted == 2;
    }

    public boolean isPending() {
        return accepted == 0;
    }

    public boolean hasCoverLetter() {
        return coverLetter != null;
    }

    public boolean hasContactNumber() {
        return contactNumber != null;
    }

    public boolean hasCreatedAt() {
        return createdAt != null;
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

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(String s) {

        try {
            this.createdAt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(s);
        } catch (ParseException e) {
            System.err.println("Could not read date from \"" + s + "\" Setting Null for CreatedAt!!!");
            this.createdAt = null;
        }
    }

    public void setIndustry(Industry industry) {
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

    public Listing getListing() {
        return listing;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Industry getIndustry() {
        return industry;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getFormattedCreatedAt() {
        return new SimpleDateFormat("dd MM yyyy").format(createdAt);
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
        dest.writeParcelable(this.listing, flags);
        dest.writeString(this.coverLetter);
        dest.writeString(this.contactNumber);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeParcelable(this.industry, flags);
        dest.writeInt(this.accepted);
    }

    protected Application(Parcel in) {
        this.id = in.readInt();
        this.donor = in.readParcelable(Donor.class.getClassLoader());
        this.charity = in.readParcelable(Charity.class.getClassLoader());
        this.listing = in.readParcelable(Listing.class.getClassLoader());
        this.coverLetter = in.readString();
        this.contactNumber = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.industry = in.readParcelable(Industry.class.getClassLoader());
        this.accepted = in.readInt();
    }

    public static final Creator<Application> CREATOR = new Creator<Application>() {
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
