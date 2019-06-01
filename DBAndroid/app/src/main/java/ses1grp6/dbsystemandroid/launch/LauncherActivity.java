package ses1grp6.dbsystemandroid.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ses1grp6.dbsystemandroid.DashboardActivity;
import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.util.TxStyler;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.util.UserType;

/**
 * This activity is the first activity launched and is responsible for launching all other activities
 * depending on saved data.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        TxStyler.initialise(getApplicationContext());
        UserData.initialise(getApplicationContext());
        UserData userData = UserData.getInstance();

        if (userData.hasData()) {
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}