package ses1grp6.dbsystemandroid.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import static android.content.Context.MODE_PRIVATE;

public class SimpleRequest extends AsyncTask<Void, Void, RequestResponse> {

    private final WeakReference<DBSystemNetwork.OnRequestComplete> callback;
    private final WeakReference<Context> context;
    private final String requestMapping;
    private final JSONObject jsonObject;
    private final MethodType method;

    public SimpleRequest(Context context, MethodType method, String requestMapping, JSONObject jsonObject, DBSystemNetwork.OnRequestComplete callback) {
        this.context = new WeakReference<>(context);
        this.callback = new WeakReference<>(callback);
        this.requestMapping = requestMapping;
        this.jsonObject = jsonObject;
        this.method = method;
    }

    @Override
    protected RequestResponse doInBackground(Void... voids) {
        return sendRequest();
    }

    @Override
    protected void onPostExecute(RequestResponse response) {

        // If the activity still exist, then call the callback.
        if (callback.get() != null) {
            callback.get().onRequestCompleted(response);
        }
    }

    private RequestResponse sendRequest() {
        // If the activity has been destroyed/closed, then don't bother downloading something, just ignore it.
        if (context.get() == null) {
            // What it returns doesn't actually matter, the callback won't be called when activity is destroyed.
            return new RequestResponse("Simple Request Callback should not have been called");
        }
        try {
            URL obj = new URL(DBSystemNetwork.API_URL + "/" + requestMapping);
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod(method.toString());

            // If a token existed in SharedPreferences then use it.
            SharedPreferences preferences = context.get().getSharedPreferences("auth", MODE_PRIVATE);
            String token = preferences.getString("token", "");
            if (!token.equals("")) {
                postConnection.setRequestProperty("Authorization", "bearer " + token);
            }

            // If it is a get request, then it can't have a body.
            if (method != MethodType.GET) {
                postConnection.setRequestProperty("Content-type", "Application/json");
                postConnection.setDoOutput(true);
                OutputStream os = postConnection.getOutputStream();
                os.write(jsonObject.toString().getBytes());
                os.flush();
                os.close();
            }

            // Get the response
            int responseCode = postConnection.getResponseCode();
            System.out.println("POST RequestResult Code :  " + responseCode);
            System.out.println("POST RequestResult Message : " + postConnection.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return new RequestResponse(responseCode, postConnection.getResponseMessage(), response.toString());
            } else {
                return new RequestResponse(responseCode);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL " + DBSystemNetwork.API_URL + "/" + requestMapping + ".");
        } catch (UnknownHostException e) {
            return new RequestResponse("Could not find the host server.");
        } catch (ConnectException e) {
            return new RequestResponse("Could not connect to the server, please try again later.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
