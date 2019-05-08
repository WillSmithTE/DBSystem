package ses1grp6.dbsystemandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    public void onLoginClicked(View view) {

        //TODO Implement proper login, below is for temporary navigation.

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
}
