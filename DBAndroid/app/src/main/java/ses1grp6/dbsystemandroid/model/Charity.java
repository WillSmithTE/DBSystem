package ses1grp6.dbsystemandroid.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Charity extends User implements Parcelable {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String CONTACT_NUMBER = "contactNumber";
    private static final String CREATED_AT = "createdAt";
    private static final String CHARITY_DESCRIPTION = "charity_description";
    private int id;
    private String name;
    private String email;
    private String contactNumber;
    private String charityDescription;
    private Date createdAt;
    private static final String INTENT_NAME = "charityModel";

    public Charity(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public Charity(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt(ID);
        this.name = jsonObject.getString(NAME);
        this.email = jsonObject.getString(EMAIL);
        if (checkNull(jsonObject, CHARITY_DESCRIPTION)) this.charityDescription = jsonObject.getString(CHARITY_DESCRIPTION);
        if (checkNull(jsonObject, CONTACT_NUMBER)) this.contactNumber = jsonObject.getString(CONTACT_NUMBER);
        if (checkNull(jsonObject, CREATED_AT)) setCreatedAt(jsonObject.getString(CREATED_AT));
    }

    public boolean search(String s) {
        return name.toLowerCase().contains(s) || charityDescription.toLowerCase().contains(s);
    }

    public boolean checkNull(JSONObject jsonObject, String key) throws JSONException{
        return jsonObject.has(key) && !jsonObject.getString(key).equals("null") && !jsonObject.getString(key).equals("") && !jsonObject.isNull(key);
    }

    @Override
    public void putToIntent(Intent intent) {
        intent.putExtra(INTENT_NAME, this);
    }

    public static Charity getFromIntent(Intent intent) {
        return intent.getParcelableExtra(INTENT_NAME);
    }

    public static boolean hasInIntent(Intent intent) {
        return intent.hasExtra(INTENT_NAME);
    }

    public boolean hasCharityDescription() {
        return charityDescription != null;
    }

    @Override
    public boolean hasName() {
        return name != null;
    }

    @Override
    public boolean hasContactNumber() {
        return contactNumber != null;
    }

    @Override
    public boolean hasCreatedAt() {
        return createdAt != null;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public void setCreatedAt(String s) {

        try {
            this.createdAt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(s);
        } catch (ParseException e) {
            System.err.println("Could not read date from \"" + s + "\" Setting Null for CreatedAt!!!");
            this.createdAt = null;
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCharityDescription() {
        return charityDescription;
    }

    public void setCharityDescription(String charityDescription) {
        this.charityDescription = charityDescription;
    }

    @Override
    public String getFormattedCreatedAt() {
        return new SimpleDateFormat("dd MMM yyyy").format(createdAt);
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
        dest.writeString(this.charityDescription);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
    }

    protected Charity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.contactNumber = in.readString();
        this.charityDescription = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
    }

    public static final Creator<Charity> CREATOR = new Creator<Charity>() {
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
