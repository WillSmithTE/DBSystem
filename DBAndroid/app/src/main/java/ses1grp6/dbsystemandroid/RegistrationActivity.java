package ses1grp6.dbsystemandroid;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        //I DONT KNOW WHAT THIS IS DOING
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    /**
     * Called when the "Sign Up" button is clicked.
     */
    public void onSignUpClicked(View view) {
        try {
            EditText email = (EditText)findViewById(R.id.emailInput);
            String result = email.getText().toString();
            POSTRequestRegister(result);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //I DONT KNOW WHAT THIS IS DOING
    public static void POSTRequestRegister(String emailText) throws IOException {
        final String POST_PARAMS = "{\n" + "\"email\": \"" + emailText + "\"" +
                "\n}";
        System.out.println(POST_PARAMS);
        //URL obj = new URL("http://localhost:8080/auth/register/");
        /**
         * THIS IS THE URL
         * LOCALHOST:8080
         * FOR DEBUGGING LOCALLY
         * HEROKU
         * https://dbsystem.herokuapp.com/
         */
        URL obj = new URL("http://local:8080/auth/register/");

        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        //postConnection.setRequestProperty("email", "test from phone");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_CREATED || responseCode == 200) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST NOT WORKED");
        }
    }

}
