package ses1grp6.dbsystemandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private String loginChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Gets the data from the intent that launches this activity.
        Intent intent = getIntent();
        loginChoice = intent.getStringExtra(DBSystemUtil.LOGIN_CHOICE);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    /**
     * Called when the "Log In" button is clicked.
     */
    public void onLoginClicked(View view) {

        //TODO Implement proper login, below is for temporary navigation.
        boolean loginAttemptSuccess = false;
        try {
            loginAttemptSuccess = POSTRequestLogin();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (loginAttemptSuccess){
            //DO START NEXT ACTIVITY
        }else{
            //LOGIN attempt fail
        }

        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra(DBSystemUtil.LOGIN_CHOICE, loginChoice);
        startActivity(intent);
    }

    /**
     * Called when the "Forgot Your Password" is clicked.
     */
    public void onForgotPasswordClicked(View view) {

    }

    /**
     * Called when "Sign Up Here" is clicked.
     */
    public void onSignUp(View view) {
        Intent signUpIntent = new Intent(this, RegistrationActivity.class);
        signUpIntent.putExtra(DBSystemUtil.LOGIN_CHOICE, loginChoice);
        startActivity(signUpIntent);
    }

    public boolean POSTRequestLogin() throws IOException {

        EditText emailET = (EditText)findViewById(R.id.emailInput);
        String resultEmailET = emailET.getText().toString();

        EditText passwordET = (EditText)findViewById(R.id.passwordInput);
        String resultPasswordET = passwordET.getText().toString();

        final String POST_PARAMS = "{\n" + "\"email\": \"" + resultEmailET + "\"," +
                "\"password\": \"" + resultPasswordET + "\"" +
                "\n}";
        System.out.println(POST_PARAMS);

        URL obj = new URL(DBSystemUtil.API_URL + "/auth/login/");
        System.out.println(obj);
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        System.out.println("HERE");
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
            return parseJson(response.toString());
        } else {
            System.out.println("POST NOT WORKED");
        }
        return false;
    }

    public boolean parseJson(String json){
        try {
            JSONObject obj = new JSONObject(json);
            String status = obj.getString("status");

            System.out.println(status);
            if (status.equals("SUCCESS")){
                String token = obj.getString("body");
                storeToken(token);
                return true;
            }
        }
        catch (JSONException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void storeToken(String token){
        SharedPreferences preferences = getSharedPreferences("tokenPref", MODE_PRIVATE);
        preferences.edit().putString("token", token).apply();

        //String token = preferences.getString("token","");
    }
}
