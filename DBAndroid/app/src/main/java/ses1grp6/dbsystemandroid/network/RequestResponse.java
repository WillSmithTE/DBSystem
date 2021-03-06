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
        this.data = null;
    }

    public RequestResponse(int code) {
        this.code = code;
        this.message = null;
        this.data = null;
    }

    public boolean isConnectionSuccessful() {
        return code >= 200 && code < 300;
    }

    public boolean hasData() {
        return data != null;
    }

    public boolean dataIsJsonArray() {
        return hasData() && data.charAt(0) == '[';
    }

    public boolean dataIsJsonObject() {
        return hasData() && data.charAt(0) == '{';
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
     * @return true if the response is a json and has a status property.
     */
    public boolean hasStatusMessage() {
        return dataIsJsonObject() && getJsonObject().has("status");
    }

    /**
     * Check if the data contains a JSON object with a status that says "SUCCESS".
     */
    public boolean hasStatusSuccessful() {

        if (!isConnectionSuccessful() || !dataIsJsonObject()) return false;
        try {
            JSONObject jsonObject = getJsonObject();

            if (jsonObject.has("status")) {
                return jsonObject.getString("status").equals("SUCCESS");
            } else {
                return false;
            }
        } catch (JSONException e) {
            System.err.println("Could not read status from response, JSON property does not exist.");
            return false;
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
            JSONObject jsonObject = getJsonObject();

            if (jsonObject.has("status") && jsonObject.has("body")) {
                return jsonObject.getString("body");
            } else {
                System.err.println("Trying to get status message but the json object does not have status message or status property");
                return "No Status Message";
            }
        } catch (JSONException e) {

            if (isConnectionSuccessful()) {
                throw new RuntimeException("Cannot read status message from a non-json data");
            } else {
                throw new RuntimeException("Cannot read status from a unsuccessful connection");
            }
        }
    }

    /**
     * @return a string containing a displayable error message if connection is unsuccessful or status is not SUCCESS.
     */
    public String getErrorMessage() {

        if (isConnectionSuccessful() && hasStatusMessage()) {
            return getStatusMessage();
        } else if (code == 404) {
            return "Error 404: Request mapping not found.";
        } else if (code == -1) {
            return "Bad connection: " + message;
        } else {
            return "Error code: " + code;
        }
    }

    /**
     * Convenience method for getting a JSON object from the body of a response object.
     */
    public JSONObject getBodyJsonObject() {
        JSONObject jsonObject = getJsonObject();

        try {
            return jsonObject.getJSONObject("body");
        } catch (JSONException e) {
            System.err.println("Could not read body from response, JSON property does not exist.");
            return new JSONObject();
        }
    }

    /**
     * Convenience method for getting a JSON array from the body of a response object.
     */
    public JSONArray getBodyJsonArray() {
        JSONObject jsonObject = getJsonObject();

        try {
            return jsonObject.getJSONArray("body");
        } catch (JSONException e) {
            System.err.println("Could not read body from response, JSON property does not exist.");
            return new JSONArray();
        }
    }
}
