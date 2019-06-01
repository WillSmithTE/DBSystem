package ses1grp6.dbsystemandroid.donor;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.charity.CharityWizard;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.util.simpleResult.ResultData;
import ses1grp6.dbsystemandroid.util.simpleResult.SimpleResultActivity;

public class donorWizard extends AppCompatActivity {
    Intent intent;
    Listing listing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_wizard);
        intent = getIntent();
        listing = Listing.getFromIntent(intent);



    }



    public void onApplyClick(View view){
        TextInputLayout coverLetterET = (TextInputLayout) findViewById(R.id.coverLetter);
        EditText contactNumberET = (EditText) findViewById(R.id.contactNumber);
        String coverLetter = coverLetterET.getEditText().getText().toString();
        String contactNumber = contactNumberET.getText().toString();
        int donor_id = UserData.getInstance().getId();
        int listing_id = listing.getId();
        request(coverLetter, contactNumber, donor_id, listing_id);

    }


    public void request(String coverLetter, String contactNumber, int donor_id, int listing_id){
//        JSONObject jsonObject = new JSONObject();
//        JSONObject jsonObjectIndustry = new JSONObject();
//        String charwizTitle = ((EditText) findViewById(R.id.charwizTitle)).getText().toString();
//        String charwizDescription = ((EditText) findViewById(R.id.charwizDescription)).getText().toString();
//        //String charwizDateTime = ((EditText) findViewById(R.id.charwizDateTime)).getText().toString();
//        String charwizLocation = ((EditText) findViewById(R.id.charwizLocation)).getText().toString();
//        //String charwizIndustry = ((EditText) findViewById(R.id.charwizIndustry)).getText().toString();
//        int charwizIndustryID = indices.get(((Spinner) findViewById(R.id.charwizSpinner)).getSelectedItemPosition());
//        String charwizIndustryName = industries.get(((Spinner) findViewById(R.id.charwizSpinner)).getSelectedItemPosition());
        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObjectIndustry.put("industryID", charwizIndustryID);
//            jsonObjectIndustry.put("industryName", charwizIndustryName);
//
//            jsonObject.put("charity", UserData.getInstance().getId());
//            jsonObject.put("listingTitle", charwizTitle);
//            jsonObject.put("listingDescription", charwizDescription);
//            jsonObject.put("location", charwizLocation);
//            jsonObject.put("industry", jsonObjectIndustry);

            jsonObject.put("coverLetter", coverLetter);
            jsonObject.put("contactNumber", contactNumber);
            jsonObject.put("donor", donor_id);
            jsonObject.put("charityListing", listing_id);
            jsonObject.put("charity", listing.getCharity().getId());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        DBSystemNetwork.sendPostRequest(this, "/listing/apply/", jsonObject, new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.hasStatusSuccessful()) {

                    Intent intent = new Intent(donorWizard.this, SimpleResultActivity.class);
                    ResultData resultData = new ResultData("Application Successful",
                            "Please wait for a response");
                    resultData.putToIntent(intent);
                    startActivity(intent);
                } else {
                    Toast.makeText(donorWizard.this, response.getErrorMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        System.out.println("REQUEST IS LPO: " + jsonObject.toString());

    }
}
