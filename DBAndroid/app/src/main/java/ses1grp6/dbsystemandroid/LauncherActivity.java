package ses1grp6.dbsystemandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This activity is the first activity launched and is responsible for launching all other activities
 * depending on saved data.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }
}
