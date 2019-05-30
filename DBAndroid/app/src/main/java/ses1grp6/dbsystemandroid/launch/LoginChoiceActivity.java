package ses1grp6.dbsystemandroid.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.launch.LoginActivity;

// This is no longer needed since the choice of login is not needed anymore unless you are
// registering. HOWEVER, this is kept here anyways in the case that it will be implemented.
public class LoginChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);
    }

    public void onDonorLoginClicked(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        //intent.putExtra(DBSystemUtil.LOGIN_CHOICE, DBSystemUtil.LOGIN_DONOR_CHOICE);
        startActivity(intent);
    }

    public void onCharityLoginClicked(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        //intent.putExtra(DBSystemUtil.LOGIN_CHOICE, DBSystemUtil.LOGIN_CHARITY_CHOICE);
        startActivity(intent);
    }
}
