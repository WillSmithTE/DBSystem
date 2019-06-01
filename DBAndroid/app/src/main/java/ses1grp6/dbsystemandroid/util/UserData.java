package ses1grp6.dbsystemandroid.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ses1grp6.dbsystemandroid.model.Charity;
import ses1grp6.dbsystemandroid.model.Donor;
import ses1grp6.dbsystemandroid.model.User;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class UserData {

    private static UserData instance;
    private final Context appContext;
    private int id = -1;
    private String token;
    private UserType userType;
    private User user;

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

    public void fetchUser(final FragmentActivity activity, final OnUserRecievedListener listener) {
        String requestMapping = userType == UserType.CHARITY ? "charity/" : "donor/";

        if (user != null) {
            listener.onUserReceived(user);
        }

        DBSystemNetwork.sendGetRequest(activity, requestMapping + UserData.getInstance().getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.hasStatusSuccessful()) {
                    JSONObject body = response.getBodyJsonObject();

                    try {
                        if (userType == UserType.CHARITY) {
                            user = new Charity(body);
                            listener.onUserReceived(user);
                        } else {
                            user = new Donor(body);
                            listener.onUserReceived(user);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(activity.getApplicationContext(), "Unable to read user profile from server", Toast.LENGTH_LONG).show();
                        System.err.println(e);
                    }
                } else {
                    Toast.makeText(activity.getApplicationContext(), "Unable to get user profile from server", Toast.LENGTH_LONG).show();
                    System.err.println(response.getErrorMessage());
                }
            }
        });
    }

    public void setData(RequestResponse response) throws JSONException {
        JSONObject body = response.getBodyJsonObject();
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

    public interface OnUserRecievedListener {

        void onUserReceived(User user);
    }
}

