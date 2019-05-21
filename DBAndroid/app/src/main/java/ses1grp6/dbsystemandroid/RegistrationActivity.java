package ses1grp6.dbsystemandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;


public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    /**
     * Called when the "Sign Up" button is clicked.
     */
    public void onSignUpClicked(View view) {
        if (!isPasswordMatching()){
            Toast.makeText(getApplicationContext(),"Passwords are not matching",Toast.LENGTH_SHORT).show();
        } else {
            register();
        }
    }

    private boolean isPasswordMatching(){
        EditText passwordET = (EditText)findViewById(R.id.passwordInput);
        EditText confirmPasswordET = (EditText)findViewById(R.id.confirmPasswordInput);

        return (passwordET.getText().toString().equals(confirmPasswordET.getText().toString()));
    }

    public void register() {

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

        RadioGroup accountType = (RadioGroup) findViewById(R.id.accountTypeRG);

        JSONObject postParams = new JSONObject();

        try {
            postParams.put("email", resultEmailET);
            postParams.put("name", resultFirstNameET + " " + resultLastNameET);
            //postParams.putToIntent("lastName", resultLastNameET);
            postParams.put("contactNumber", resultPhoneNumberET);
            postParams.put("password", resultPasswordET);
            if (accountType.getCheckedRadioButtonId() == R.id.radioButton1){
                postParams.put("isCharity", false);
            }
            else {
                postParams.put("isCharity", true);
            }
        } catch (JSONException e) {
            throw new RuntimeException("Registration Request creation has the wrong JSON format.");
        }

        DBSystemNetwork.sendPostRequest(this, "/auth/register", postParams, new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.isConnectionSuccessful() && response.hasStatusSuccessful()) {
                    showRegistrationResult();
                } else {
                    Toast.makeText(RegistrationActivity.this, response.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showRegistrationResult() {
        Intent intent = new Intent(this, RegistrationResultActivity.class);
        startActivity(intent);
    }
}
