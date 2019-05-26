package ses1grp6.dbsystemandroid;

import android.content.Intent;
import android.content.SharedPreferences;

public enum LoginChoice {
    DONOR(0),
    CHARITY(1);

    public static final String NAME = "loginChoice";
    private final int id;

    LoginChoice(int id) {
        this.id = id;
    }

    public static LoginChoice getFromIntent(Intent intent) {
        return intent.getIntExtra(NAME, 0) == 0 ? DONOR : CHARITY;
    }

    public void putToIntent(Intent intent) {
        intent.putExtra(NAME, this.id);
    }

    public void putToPreferences(SharedPreferences preferences) {
        preferences.edit().putInt(NAME, this.id).apply();
    }

    public static LoginChoice getFromPreferences(SharedPreferences preferences) {
        return preferences.getInt(NAME, 0) == 0 ? DONOR : CHARITY;
    }
}
