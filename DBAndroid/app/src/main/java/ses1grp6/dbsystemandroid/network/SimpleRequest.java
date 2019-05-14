package ses1grp6.dbsystemandroid.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import ses1grp6.dbsystemandroid.DBSystemUtil;

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

    private RequestResponse sendRequest(){

        try {
            // If the activity has been destroyed/closed, then don't bother downloading something, just ignore it.
            if (context.get() == null) {
                return new RequestResponse("Activity has been destroyed!");
            }

            URL obj = new URL(DBSystemNetwork.API_URL + "/" + requestMapping);
            System.out.println(obj);
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod(method.toString());
            postConnection.setRequestProperty("Content-type", "Application/json");

            // If a token existed in SharedPreferences then use it.
            SharedPreferences preferences = context.get().getSharedPreferences(DBSystemUtil.APP_ID, MODE_PRIVATE);
            String token = preferences.getString("token","");
            if (!token.equals("")) {
                postConnection.setRequestProperty("Authorization", "bearer " + token);
            }

            // If it is a get request, then it can't have a body.
            if (method != MethodType.GET) {
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
            if (responseCode == HttpURLConnection.HTTP_CREATED || responseCode == 200) {
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
                System.out.println("POST NOT WORKED");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new RequestResponse(""); // TODO Fix exceptions, This is bad, it dosen't actually return an error and the person on the other side might have problems.
    }

}
