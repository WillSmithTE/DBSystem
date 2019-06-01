package ses1grp6.dbsystemandroid.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listing implements Parcelable {

    private static final String INTENT_NAME = "listingModel";
    private static final String ID = "id";
    private static final String CONTACT_NUMBER = "contactNumber";
    private static final String LISTING_DESCRIPTION = "listingDescription";
    private static final String INDUSTRY = "industry";
    private static final String CREATED_AT = "createdAt";
    private static final String LOCATION = "location";
    private static final String LISTING_TITLE = "listingTitle";
    private static final String CHARITY = "charity";
    private static final String EVENT_START_DATE = "eventStartDate";
    private static final String EVENT_END_DATE = "eventEndDate";
    private static final String EXPIRES_AT = "expires_at";
    private int id;
    private Charity charity;
    private String listingTitle;
    private String contactNumber;
    private String listingDescription;
    private String location;
    private Date createdAt;
    private Date eventStartDate;
    private Date eventEndDate;
    private String industry;
    private Date expiresAt;


    public Listing(int id, Charity charity) {
        this.id = id;
        this.charity = charity;
    }

    public Listing(JSONObject jsonObject) throws JSONException {
        this.listingTitle = jsonObject.getString(LISTING_TITLE);
        this.listingDescription = jsonObject.getString(LISTING_DESCRIPTION);
        this.location = jsonObject.getString(LOCATION);
        setCreatedAt(jsonObject.getString(CREATED_AT));
        this.id = jsonObject.getInt(ID);
        this.charity = new Charity(jsonObject.getJSONObject(CHARITY));
        if (checkNull(jsonObject, CONTACT_NUMBER)) this.contactNumber = jsonObject.getString(CONTACT_NUMBER);
        if (checkNull(jsonObject, INDUSTRY)) this.industry = jsonObject.getString(INDUSTRY);
        if (checkNull(jsonObject, EVENT_START_DATE)) setEventStartDate(jsonObject.getString(EVENT_START_DATE));
        if (checkNull(jsonObject, EVENT_END_DATE)) setEventEndDate(jsonObject.getString(EVENT_END_DATE));
        if (checkNull(jsonObject, EXPIRES_AT)) setExpiresAt(jsonObject.getString(EXPIRES_AT));
    }

    /**
     * Null checks delegated.
     */
    public boolean checkNull(JSONObject jsonObject, String key) throws JSONException{
        return jsonObject.has(key) && !jsonObject.getString(key).equals("null") && !jsonObject.getString(key).equals("") && !jsonObject.isNull(key);
    }

    public boolean hasListingTitle() {
        return listingTitle != null;
    }

    public boolean hasContactNumber() {
        return contactNumber != null;
    }

    public boolean hasListingDescription() {
        return listingDescription != null;
    }

    public boolean hasEventStartDate() {
        return eventStartDate != null;
    }

    public boolean hasEventEndDate() {
        return eventEndDate != null;
    }

    public boolean hasExpiresAt() {
        return expiresAt != null;
    }

    public boolean hasLocation() {
        return location != null;
    }

    public boolean hasIndustry() {
        return industry != null;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    private Date getDateFromString(String s) {

        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(s);
        } catch (ParseException e) {
            System.err.println("Could not read date from \"" + s + "\" Returning Null!!!");
            return null;
        }
    }

    public void setCreatedAt(String s) {
        this.createdAt = getDateFromString(s);
    }

    public void setEventStartDate(String s) {
        this.eventStartDate = getDateFromString(s);
    }

    public void setEventEndDate(String s) {
        this.eventEndDate = getDateFromString(s);
    }

    public void setExpiresAt(String s) {
        this.eventEndDate = getDateFromString(s);
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd MM yyyy").format(date);
    }

    public String getFormattedCreatedAt() {
        return formatDate(createdAt);
    }

    public String getFormattedStartDate() {
        return formatDate(eventStartDate);
    }

    public String getFormattedExpiresAt() {
        return formatDate(expiresAt);
    }

    public String getFormattedEndDate() {
        return formatDate(eventEndDate);
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
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
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
        this.createdAt = tmpTimestamp == -1 ? null : new Date(tmpTimestamp);
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
