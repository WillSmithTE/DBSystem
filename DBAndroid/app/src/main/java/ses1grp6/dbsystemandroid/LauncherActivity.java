package ses1grp6.dbsystemandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * This activity is the first activity launched and is responsible for launching all other activities
 * depending on saved data.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        // TODO EVENTUALLY SKIP LOGIN IF ALREADY LOGGED IN ON THE DEVICE.


        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

//        Intent loginIntent = new Intent(this, LoginChoiceActivity.class);
//        startActivity(loginIntent);
    }
}
