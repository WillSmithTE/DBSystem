package ses1grp6.dbsystemandroid.charity.model;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listing implements Parcelable {

    private static final String INTENT_NAME = "listingModel";
    private static final String ID = "id";
    private static final String CONTACT_NUMBER = "contactNumber";
    private static final String LISTING_DESCRIPTION = "listingDescription";
    private static final String INDUSTRY = "industry";
    private static final String TIMESTAMP = "timestamp";
    private static final String LOCATION = "location";
    private static final String LISTING_TITLE = "listingTitle";
    private static final String CHARITY = "charity";
    private int id;
    private Charity charity;
    private String listingTitle;
    private String contactNumber;
    private String listingDescription;
    private String location;
    private Date timestamp;
    private String industry;


    public Listing(int id, Charity charity) {
        this.id = id;
        this.charity = charity;
    }

    public Listing(JSONObject obj) throws JSONException, ParseException {
        this.listingTitle = obj.getString(LISTING_TITLE);
        this.listingDescription = obj.getString(LISTING_DESCRIPTION);
        this.location = obj.getString(LOCATION);
        setTimestamp(obj.getString(TIMESTAMP));
        this.id = obj.getInt(ID);
        this.charity = new Charity(obj.getJSONObject(CHARITY));
        this.contactNumber = obj.getString(CONTACT_NUMBER);
        this.industry = obj.getString(INDUSTRY);
    }

    public void putToIntent(Intent intent) {
        intent.putExtra(INTENT_NAME, this);
    }

    public static Listing getFromIntent(Intent intent) {
        return intent.getParcelableExtra(INTENT_NAME);
    }

    public void setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }

    public void setLocation(String location) {
        this.location = location;
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
        dest.writeParcelable(this.charity, flags);
        dest.writeString(this.listingTitle);
        dest.writeString(this.contactNumber);
        dest.writeString(this.listingDescription);
        dest.writeString(this.location);
        dest.writeLong(this.timestamp != null ? this.timestamp.getTime() : -1);
        dest.writeString(this.industry);
    }

    protected Listing(Parcel in) {
        this.id = in.readInt();
        this.charity = in.readParcelable(Charity.class.getClassLoader());
        this.listingTitle = in.readString();
        this.contactNumber = in.readString();
        this.listingDescription = in.readString();
        this.location = in.readString();
        long tmpTimestamp = in.readLong();
        this.timestamp = tmpTimestamp == -1 ? null : new Date(tmpTimestamp);
        this.industry = in.readString();
    }

    public static final Parcelable.Creator<Listing> CREATOR = new Parcelable.Creator<Listing>() {
        @Override
        public Listing createFromParcel(Parcel source) {
            return new Listing(source);
        }

        @Override
        public Listing[] newArray(int size) {
            return new Listing[size];
        }
    };
}
