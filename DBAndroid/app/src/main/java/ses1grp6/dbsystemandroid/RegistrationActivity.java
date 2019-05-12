package ses1grp6.dbsystemandroid;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    //I DONT KNOW WHAT THIS IS DOING
    public void POSTRequestRegister() throws IOException {

        EditText emailET = (EditText)findViewById(R.id.emailInput);
        String resultEmailET = emailET.getText().toString();

        EditText firstNameET = (EditText)findViewById(R.id.firstNameInput);
        String resultFirstNameET = firstNameET.getText().toString();

        EditText lastNameET = (EditText)findViewById(R.id.lastNameInput);
        String resultLastNameET = lastNameET.getText().toString();

        EditText phoneNumberET = (EditText)findViewById(R.id.phoneNumberInput);
        String resultPhoneNumberET = phoneNumberET.getText().toString();

        EditText passwordET = (EditText)findViewById(R.id.passwordInput);
        String resultPasswordET = passwordET.getText().toString();

        final String POST_PARAMS = "{\n" + "\"email\": \"" + resultEmailET + "\"," +
                "\"firstName\": \"" + resultFirstNameET + "\"," +
                "\"lastName\": \"" + resultLastNameET + "\"," +
                "\"contactNumber\": \"" + resultPhoneNumberET + "\"," +
                "\"password\": \"" + resultPasswordET + "\"" +
                "\n}";
        System.out.println(POST_PARAMS);
        URL obj = new URL(DBSystemUtil.API_URL + "/auth/register/");

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
