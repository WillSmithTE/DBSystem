package ses1grp6.dbsystemandroid;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import ses1grp6.dbsystemandroid.network.RequestResponse;

public class UserData {

    private static UserData instance;
    private final Context appContext;
    private int id = -1;
    private String token;
    private UserType userType;

    private UserData(Context appContext) {
        this.appContext = appContext;
    }

    /**
     * Needs to be called before using UserData instance.
     * @param appContext Application context.
     */
    public static void initialise(Context appContext) {

        if (instance != null) {
            throw new RuntimeException("Trying to initialise UserData more than once?");
        } else {
            instance = new UserData(appContext);
            instance.fetchFromSharedPreferences();
        }
    }

    public static UserData getInstance() {

        if (instance == null) {
            throw new RuntimeException("UserData.initialise needs to be called in the app first before using UserData");
        } else {
            return instance;
        }
    }

    public void setData(RequestResponse response) throws JSONException {
        JSONObject body = response.getBody();
        id = body.getInt("userId");
        token = body.getString("token");
        String userTypeString = body.getString("userType");
        userType = UserType.getFromString(userTypeString);
        storeToSharedPreferences();
    }

    public void setData(int id, String token, UserType userType) {
        this.id = id;
        this.token = token;
        this.userType = userType;
        storeToSharedPreferences();
    }

    private void storeToSharedPreferences() {
        SharedPreferences preferences = appContext.getSharedPreferences("auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", id);
        editor.putString("token", token);
        editor.apply();
        userType.putToPreferences(preferences);
    }

    private void fetchFromSharedPreferences() {

        // Check if the UserData instance contains user data.
        if (id == -1) {
            SharedPreferences preferences = appContext.getSharedPreferences("userData", Context.MODE_PRIVATE);
            int id = preferences.getInt("id", -1);

            // Check if SharedPreferences contains user data.
            if (id != -1) {
                token = preferences.getString("token", "");
                userType = UserType.getFromPreferences(preferences);
            }
        }
    }

    public void clearStoredUserData() {
        appContext.getSharedPreferences("userData", Context.MODE_PRIVATE).edit().clear().apply();
    }

    public boolean hasData() {
        return id != -1;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public UserType getUserType() {
        return userType;
    }
}

