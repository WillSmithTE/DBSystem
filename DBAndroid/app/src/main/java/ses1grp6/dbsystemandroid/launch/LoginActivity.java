package ses1grp6.dbsystemandroid.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ses1grp6.dbsystemandroid.DashboardActivity;
import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.UserData;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Called when the "Log In" button is clicked.
     */
    public void onLoginClicked(View view) {
        POSTRequestLogin();
    }

    private void POSTRequestLogin() {
        JSONObject postParams = new JSONObject();
        try {
            postParams.put("email", ((EditText)findViewById(R.id.emailInput)).getText().toString());
            postParams.put("password", ((EditText)findViewById(R.id.passwordInput)).getText().toString());
        } catch (JSONException e) {

        }

        DBSystemNetwork.sendPostRequest("auth/login/", postParams, new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.hasStatusSuccessful()) {
                    try {
                        UserData userData = UserData.getInstance();
                        userData.setData(response); // May throw JSONException.
                        changeToDashboard();
                    }
                    catch (JSONException e) {
                        Toast.makeText(LoginActivity.this, "Unable to get proper message from server.",
                                Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, response.getErrorMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
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
        startActivity(signUpIntent);
    }

    private void changeToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
