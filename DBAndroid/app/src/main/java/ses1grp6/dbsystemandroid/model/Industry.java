package ses1grp6.dbsystemandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Industry implements Parcelable {

    private static final String ID = "industryId";
    private static final String INDUSTRY_NAME = "industryName";
    private int id;
    private String industryName;

    public Industry(int id, String industryName) {
        this.id = id;
        this.industryName = industryName;
    }

    public Industry(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt(ID);
        this.industryName = jsonObject.getString(INDUSTRY_NAME);
    }

    @Override
    public String toString() {
        return getIndustryName();
    }

    public int getId() {
        return id;
    }

    public String getIndustryName() {
        return industryName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.industryName);
    }

    protected Industry(Parcel in) {
        this.id = in.readInt();
        this.industryName = in.readString();
    }

    public static final Parcelable.Creator<Industry> CREATOR = new Parcelable.Creator<Industry>() {
        @Override
        public Industry createFromParcel(Parcel source) {
            return new Industry(source);
        }

        @Override
        public Industry[] newArray(int size) {
            return new Industry[size];
        }
    };
}
