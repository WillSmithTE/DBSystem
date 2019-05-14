package ses1grp6.dbsystemandroid.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestResponse {

    public final int code;
    public final String message;
    public final String data;

    public RequestResponse(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RequestResponse(String message) {
        this.message = message;
        this.code = -1;
        this.data = "";
    }

    public boolean isConnectionSuccessful() {
        return code >= 200 && code < 300;
    }

    public boolean hasData() {
        return !data.equals("");
    }

    public boolean dataIsJsonArray() {
        return data.charAt(0) == '[';
    }

    public boolean dataIsJsonObject() {
        return data.charAt(0) == '{';
    }

    public JSONObject getJsonObject() {

        try {
            return new JSONObject(data);
        } catch (JSONException e) {
            throw new RuntimeException("Could not turn response data into a JSON Object");
        }
    }

    public JSONArray getJsonArray() {

        try {
            return new JSONArray(data);
        } catch (JSONException e) {
            throw new RuntimeException("Could not turn the response data into a JSON Array");
        }
    }

    /**
     * Check if the data contains a JSON object with a status that says success.
     *
     * May throw RuntimeException if there was no successful connection, if the data is not a
     * JSON object or if there is no such property in the JSON.
     */
    public boolean isStatusSuccessful() {

        try {
            JSONObject jsonObject = new JSONObject(data);

            if (jsonObject.has("status")) {
                return jsonObject.getString("status").equals("SUCCESS");
            } else {
                throw new RuntimeException("Trying to get status but the json object does not have status property");
            }
        } catch (JSONException e) {

            if (isConnectionSuccessful()) {
                throw new RuntimeException("Cannot read status from a non-json data");
            } else {
                throw new RuntimeException("Cannot read status from a unsuccessful connection");
            }
        }
    }

    /**
     * Get the status message from a JSON object.
     *
     * May throw RuntimeException if there was no successful connection, if the data is not a
     * JSON object or if there is no such property in the JSON.
     */
    public String getStatusMessage() {

        try {
            JSONObject jsonObject = new JSONObject(data);

            if (jsonObject.has("status") && jsonObject.has("body")) {
                return jsonObject.getString("body");
            } else {
                throw new RuntimeException("Trying to get status message but the json object does not have status message or status property");
            }
        } catch (JSONException e) {

            if (isConnectionSuccessful()) {
                throw new RuntimeException("Cannot read status message from a non-json data");
            } else {
                throw new RuntimeException("Cannot read status from a unsuccessful connection");
            }
        }
    }
}
