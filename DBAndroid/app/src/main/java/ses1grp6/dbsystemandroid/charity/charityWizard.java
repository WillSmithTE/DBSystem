package ses1grp6.dbsystemandroid.charity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.simpleResult.ResultData;
import ses1grp6.dbsystemandroid.util.simpleResult.SimpleResultActivity;

public class charityWizard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_wizard);
    }

    public void onPostClick(View view) {
        JSONObject jsonObject = new JSONObject();
        String charwizTitle = ((EditText) findViewById(R.id.charwizTitle)).getText().toString();
        String charwizDescription = ((EditText) findViewById(R.id.charwizDescription)).getText().toString();
        String charwizDateTime = ((EditText) findViewById(R.id.charwizDateTime)).getText().toString();
        String charwizLocation = ((EditText) findViewById(R.id.charwizLocation)).getText().toString();
        String charwizIndustry = ((EditText) findViewById(R.id.charwizIndustry)).getText().toString();
        try {
            jsonObject.put("listingTitle", charwizTitle);
            jsonObject.put("listingDescription", charwizDescription);
            jsonObject.put("location", charwizLocation);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        DBSystemNetwork.sendPostRequest("listing/new/", jsonObject, new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.hasStatusSuccessful()) {

                    Intent intent = new Intent(charityWizard.this, SimpleResultActivity.class);
                    new ResultData("Listing Added", "");
                    startActivity(intent);
                } else {
                    Toast.makeText(charityWizard.this, response.getErrorMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
