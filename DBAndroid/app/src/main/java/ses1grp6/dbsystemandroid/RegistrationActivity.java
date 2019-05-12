package ses1grp6.dbsystemandroid;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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

        if (!isPasswordMatching()){
            Toast.makeText(getApplicationContext(),"Passwords not matching",Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                POSTRequestRegister();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    private boolean isPasswordMatching(){
        EditText passwordET = (EditText)findViewById(R.id.passwordInput);
        EditText confirmPasswordET = (EditText)findViewById(R.id.confirmPasswordInput);

        return (passwordET.getText().toString().equals(confirmPasswordET.getText().toString()));
    }

    public boolean POSTRequestRegister() throws IOException {

        EditText emailET = (EditText) findViewById(R.id.emailInput);
        String resultEmailET = emailET.getText().toString();

        EditText firstNameET = (EditText) findViewById(R.id.firstNameInput);
        String resultFirstNameET = firstNameET.getText().toString();

        EditText lastNameET = (EditText) findViewById(R.id.lastNameInput);
        String resultLastNameET = lastNameET.getText().toString();

        EditText phoneNumberET = (EditText) findViewById(R.id.phoneNumberInput);
        String resultPhoneNumberET = phoneNumberET.getText().toString();

        EditText passwordET = (EditText) findViewById(R.id.passwordInput);
        String resultPasswordET = passwordET.getText().toString();

        JSONObject postParams = new JSONObject();

        try {
            postParams.put("email", resultEmailET);
            postParams.put("name", resultFirstNameET + " " + resultLastNameET);
            //postParams.put("lastName", resultLastNameET);
            postParams.put("contactNumber", resultPhoneNumberET);
            postParams.put("password", resultPasswordET);
        } catch (JSONException e) {
            return false;
        }

        RequestUtil requestUtil = new RequestUtil();
        return parseJsonSuccess(requestUtil.POSTRequest("/auth/register/", postParams));
    }

    public boolean parseJsonSuccess(JSONObject json){
        try {
            String status = json.getString("status");

            System.out.println(status);
            if (status.equals("SUCCESS")){
                return true;
            }
        }
        catch (JSONException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

}
