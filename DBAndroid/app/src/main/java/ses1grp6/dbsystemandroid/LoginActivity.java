package ses1grp6.dbsystemandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class LoginActivity extends AppCompatActivity {

    private String loginChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Gets the data from the intent that launches this activity.
        Intent intent = getIntent();
        loginChoice = intent.getStringExtra(DBSystemUtil.LOGIN_CHOICE);

    }

    /**
     * Called when the "Log In" button is clicked.
     */
    public void onLoginClicked(View view) throws IOException {
        POSTRequestLogin();
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

    private void POSTRequestLogin() throws IOException {
        JSONObject postParams = new JSONObject();
        try {
            postParams.put("email", ((EditText)findViewById(R.id.emailInput)).getText().toString());
            postParams.put("password", ((EditText)findViewById(R.id.passwordInput)).getText().toString());
        } catch (JSONException e) {

        }

        DBSystemNetwork.sendPostRequest(this, "auth/login/", postParams, new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.isStatusSuccessful()) {
                    try {
                        storeToken(response.getJsonObject().getString("body"));
                        changeActivity();
                    }
                    catch (JSONException e){
                        Toast.makeText(LoginActivity.this, response.message,
                                Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, response.message,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void changeActivity() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra(DBSystemUtil.LOGIN_CHOICE, loginChoice);
        startActivity(intent);
        DBSystemNetwork.sendGetRequest(this, "donor/", new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                System.out.println("GET Request Completed!!");
                System.out.println(response.data);
            }
        });
    }


    private void storeToken(String token){
        SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
        preferences.edit().putString("token", token).apply();
    }
}
