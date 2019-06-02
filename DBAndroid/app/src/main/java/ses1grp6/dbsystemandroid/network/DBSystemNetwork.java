package ses1grp6.dbsystemandroid.network;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

public class DBSystemNetwork {

    public static final String API_URL = "https://dbsystem.herokuapp.com/";

    /**
     * Sends a POST request with a JSON to {@link DBSystemNetwork#API_URL} plus the request mapping specified.
     * @param activity The current activity or the current activity of the fragment.
     * @param requestMapping appends {@link DBSystemNetwork#API_URL}.
     * @param jsonObject a Post request parameter, the json data to be sent.
     * @param callback a callback which will be called when a response is received. See {@link DBSystemNetwork.OnRequestComplete}.
     */
    public static void sendPostRequest(FragmentActivity activity, String requestMapping, JSONObject jsonObject, OnRequestComplete callback) {
        addNetworkFragment(activity, MethodType.POST, requestMapping, jsonObject, callback);
    }

    /**
     * Sends a GET request to {@link DBSystemNetwork#API_URL} plus the request mapping specified.
     * @param activity The current activity or the current activity of the fragment.
     * @param requestMapping appends {@link DBSystemNetwork#API_URL}.
     * @param callback a callback which will be called when a response is received. See {@link DBSystemNetwork.OnRequestComplete}.
     */
    public static void sendGetRequest(FragmentActivity activity, String requestMapping, OnRequestComplete callback) {
        addNetworkFragment(activity, MethodType.GET, requestMapping, new JSONObject(), callback);
    }

    /**
     * Sends a PUT request to {@link DBSystemNetwork#API_URL} plus the request mapping specified.
     * @param activity The current activity or the current activity of the fragment.
     * @param requestMapping appends {@link DBSystemNetwork#API_URL}.
     * @param jsonObject a Post request parameter, the json data to be sent.
     * @param callback a callback which will be called when a response is received. See {@link DBSystemNetwork.OnRequestComplete}.
     */
    public static void sendPutRequest(FragmentActivity activity, String requestMapping, JSONObject jsonObject, OnRequestComplete callback) {
        addNetworkFragment(activity, MethodType.PUT, requestMapping, jsonObject, callback);
    }

    private static void addNetworkFragment(FragmentActivity activity, MethodType methodType, String requestMapping, JSONObject jsonObject, OnRequestComplete callback) {
        FragmentTransaction fragTransaction = activity.getSupportFragmentManager().beginTransaction();
        NetworkFragment networkFragment = new NetworkFragment();
        networkFragment.setupNetworkFragment(methodType, requestMapping, jsonObject, callback);
        fragTransaction.add(networkFragment, null);
        fragTransaction.commit();
    }

    public interface OnRequestComplete {

        void onRequestCompleted(RequestResponse response);
    }
}
