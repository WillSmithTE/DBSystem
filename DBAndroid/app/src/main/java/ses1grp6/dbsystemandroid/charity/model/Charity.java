package ses1grp6.dbsystemandroid.charity.model;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Charity implements Parcelable {

    private int id;
    private String name;
    private String email;
    private String contactNumber;
    private Date timestamp;
    private static final String INTENT_NAME = "charityModel";

    public Charity(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public Charity(JSONObject obj) throws JSONException, ParseException {
        this.id = obj.getInt("id");
        this.name = obj.getString("name");
        this.email = obj.getString("email");
        this.contactNumber = obj.getString("contactNumber");
        setTimestamp(obj.getString("timestamp"));
    }

    public void putToIntent(Intent intent) {
        intent.putExtra(INTENT_NAME, this);
    }

    public static Charity getFromIntent(Intent intent) {
        return intent.getParcelableExtra(INTENT_NAME);
    }

    public boolean hasName() {
        return name != null;
    }

    public boolean hasContactNumber() {
        return contactNumber != null;
    }

    public boolean hasTimestamp() {
        return timestamp != null;
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
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(timestampString);
    }

    public int getId() {
        return id;
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
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.contactNumber);
        dest.writeLong(this.timestamp != null ? this.timestamp.getTime() : -1);
    }

    protected Charity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.contactNumber = in.readString();
        long tmpTimestamp = in.readLong();
        this.timestamp = tmpTimestamp == -1 ? null : new Date(tmpTimestamp);
    }

    public static final Parcelable.Creator<Charity> CREATOR = new Parcelable.Creator<Charity>() {
        @Override
        public Charity createFromParcel(Parcel source) {
            return new Charity(source);
        }

        @Override
        public Charity[] newArray(int size) {
            return new Charity[size];
        }
    };
}
