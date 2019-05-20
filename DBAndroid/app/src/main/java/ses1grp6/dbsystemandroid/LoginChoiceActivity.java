package ses1grp6.dbsystemandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
