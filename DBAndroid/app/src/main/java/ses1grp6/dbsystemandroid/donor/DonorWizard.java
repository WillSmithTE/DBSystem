package ses1grp6.dbsystemandroid.donor;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import ses1grp6.dbsystemandroid.R;

public class DonorWizard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_wizard);
    }

    public void onPostClick(View view) {
        JSONObject jsonObject = new JSONObject();
        String donorwizTitle = (((EditText) findViewById(R.id.charwizTitle)).getText().toString());
        //String donorDateandtime = (((EditText) findViewById(R.id.charwizDateTime)).getText().toString());
        String donorLocation = (((EditText) findViewById(R.id.charwizLocation)).getText().toString());
    }

}
