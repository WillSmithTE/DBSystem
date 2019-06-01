package ses1grp6.dbsystemandroid.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Donor implements Parcelable {

    private static final String INTENT_NAME = "donorModel";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String CONTACT_NUMBER = "contactNumber";
    private static final String ID = "id";
    private static final String CREATED_AT = "createdAt";
    private String name;
    private String email;
    private String contactNumber;
    private int id;
    private Date createdAt;

    public Donor(int id, String email) {
        this.email = email;
        this.id = id;
    }

    public Donor(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt(ID);
        this.name = jsonObject.getString(NAME);
        this.email = jsonObject.getString(EMAIL);
        if (checkNull(jsonObject, CONTACT_NUMBER)) this.contactNumber = jsonObject.getString(CONTACT_NUMBER);
        if (checkNull(jsonObject, CREATED_AT)) setCreatedAt(jsonObject.getString(CREATED_AT));
    }

    public boolean checkNull(JSONObject jsonObject, String key) throws JSONException{
        return jsonObject.has(key) && !jsonObject.getString(key).equals("null") && !jsonObject.getString(key).equals("") && !jsonObject.isNull(key);
    }

    public boolean hasName() {
        return name != null;
    }

    public boolean hasContactNumber() {
        return contactNumber != null;
    }

    public boolean hasTimestamp() {
        return createdAt != null;
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
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.contactNumber);
        dest.writeInt(this.id);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
    }

    protected Donor(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.contactNumber = in.readString();
        this.id = in.readInt();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
    }

    public static final Creator<Donor> CREATOR = new Creator<Donor>() {
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
