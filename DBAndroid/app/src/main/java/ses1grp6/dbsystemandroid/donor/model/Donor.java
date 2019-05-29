package ses1grp6.dbsystemandroid.donor.model;

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

public class Donor implements Parcelable {

    private static final String INTENT_NAME = "donorModel";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String CONTACT_NUMBER = "contactNumber";
    private static final String ID = "id";
    private static final String TIMESTAMP = "timestamp";
    private String name;
    private String email;
    private String contactNumber;
    private int id;
    private Date timestamp;

    public Donor(int id, String email) {
        this.email = email;
        this.id = id;
    }

    public Donor(JSONObject jsonObject) throws JSONException, ParseException {
        this.name = jsonObject.getString(NAME);
        this.email = jsonObject.getString(EMAIL);
        this.contactNumber = jsonObject.getString(CONTACT_NUMBER);
        this.id = jsonObject.getInt(ID);
        setTimestamp(jsonObject.getString(TIMESTAMP));
    }

    public void putToIntent(Intent intent) {
        intent.putExtra(INTENT_NAME, this);
    }

    public static Donor getFromIntent(Intent intent) {
        return intent.getParcelableExtra(INTENT_NAME);
    }

    public void setName(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getId() {
        return id;
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
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.contactNumber);
        dest.writeInt(this.id);
        dest.writeLong(this.timestamp != null ? this.timestamp.getTime() : -1);
    }

    protected Donor(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.contactNumber = in.readString();
        this.id = in.readInt();
        long tmpTimestamp = in.readLong();
        this.timestamp = tmpTimestamp == -1 ? null : new Date(tmpTimestamp);
    }

    public static final Parcelable.Creator<Donor> CREATOR = new Parcelable.Creator<Donor>() {
        @Override
        public Donor createFromParcel(Parcel source) {
            return new Donor(source);
        }

        @Override
        public Donor[] newArray(int size) {
            return new Donor[size];
        }
    };
}
