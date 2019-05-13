package ses1grp6.dbsystemandroid;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestUtil {
    public JSONObject POSTRequest(String requestMapping, JSONObject postParams){ // Returns JSON, if fail returns empty
        try {
            URL obj = new URL(DBSystemUtil.API_URL + requestMapping);
            System.out.println(obj);
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();
            os.write(postParams.toString().getBytes());
            os.flush();
            os.close();
            int responseCode = postConnection.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            System.out.println("POST Response Message : " + postConnection.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_CREATED || responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response.toString());
                return new JSONObject(response.toString());
            } else {
                System.out.println("POST NOT WORKED");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new JSONObject();
    }
}
