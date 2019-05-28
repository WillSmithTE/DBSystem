package ses1grp6.dbsystemandroid.util;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public enum UserType {
    DONOR("donor"),
    CHARITY("charity");

    public static final String NAME = "userType";
    private final String id;

    UserType(String id) {
        this.id = id;
    }

    @Nullable
    public static UserType getFromIntent(Intent intent) {
        return getFromString(intent.getStringExtra(NAME));
    }

    @Nullable
    public static UserType getFromString(String s) {

        if (s.toLowerCase().equals(DONOR.toString())) {
            return DONOR;
        } else if (s.toLowerCase().equals(CHARITY.toString())) {
            return CHARITY;
        }
        return null;
    }

    public void putToIntent(Intent intent) {
        intent.putExtra(NAME, this.id);
    }

    public void putToPreferences(SharedPreferences preferences) {
        preferences.edit().putString(NAME, this.id).apply();
    }

    @Nullable
    public static UserType getFromPreferences(SharedPreferences preferences) {
        return getFromString(preferences.getString(NAME, "none"));
    }

    @Override
    public String toString() {
        return id;
    }
}
