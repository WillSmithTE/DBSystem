package ses1grp6.dbsystemandroid.network;

import org.json.JSONObject;

public class DBSystemNetwork {

    public static final String API_URL = "http://172.19.157.0:8080";

    /**
     * Sends a POST request with a JSON to {@link DBSystemNetwork#API_URL} plus the request mapping specified.
     * @param requestMapping appends {@link DBSystemNetwork#API_URL}.
     * @param jsonObject a Post request parameter, the json data to be sent.
     * @param callback a callback which will be called when a response is received. See {@link DBSystemNetwork.OnRequestComplete}.
     */
    public static void sendPostRequest(String requestMapping, JSONObject jsonObject, OnRequestComplete callback) {
        new SimpleRequest(MethodType.POST, requestMapping, jsonObject, callback).execute();
    }

    /**
     * Sends a GET request to {@link DBSystemNetwork#API_URL} plus the request mapping specified.
     * @param requestMapping appends {@link DBSystemNetwork#API_URL}.
     * @param callback a callback which will be called when a response is received. See {@link DBSystemNetwork.OnRequestComplete}.
     */
    public static void sendGetRequest(String requestMapping, OnRequestComplete callback) {
        new SimpleRequest(MethodType.GET, requestMapping, new JSONObject(), callback).execute();
    }

    /**
     * Sends a PUT request to {@link DBSystemNetwork#API_URL} plus the request mapping specified.
     * @param requestMapping appends {@link DBSystemNetwork#API_URL}.
     * @param callback a callback which will be called when a response is received. See {@link DBSystemNetwork.OnRequestComplete}.
     */
    public static void sendPutRequest(String requestMapping, JSONObject jsonObject, OnRequestComplete callback) {
        new SimpleRequest(MethodType.PUT, requestMapping, jsonObject, callback).execute();
    }

    public interface OnRequestComplete {

        void onRequestCompleted(RequestResponse response);
    }
}
