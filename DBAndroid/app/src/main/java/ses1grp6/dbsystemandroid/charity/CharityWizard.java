package ses1grp6.dbsystemandroid.charity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.util.simpleResult.ResultData;
import ses1grp6.dbsystemandroid.util.simpleResult.SimpleResultActivity;

public class CharityWizard extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
    Spinner spinner;
    ArrayList<String> industries;
    ArrayList<Integer> indices;
    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spinner = (Spinner) findViewById(R.id.charwizSpinner);
        setupSpinner();

        setContentView(R.layout.activity_charity_wizard);

        dateText = findViewById(R.id.dateshow);
        findViewById(R.id.charwizstartDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE)

        );
        datePickerDialog.show();
    }

    public void onPostClick(View view) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectIndustry = new JSONObject();
        String charwizTitle = ((EditText) findViewById(R.id.charwizTitle)).getText().toString();
        String charwizDescription = ((EditText) findViewById(R.id.charwizDescription)).getText().toString();
        //String charwizDateTime = ((EditText) findViewById(R.id.charwizDateTime)).getText().toString();
        String charwizLocation = ((EditText) findViewById(R.id.charwizLocation)).getText().toString();
        //String charwizIndustry = ((EditText) findViewById(R.id.charwizIndustry)).getText().toString();
        int charwizIndustryID = indices.get(((Spinner) findViewById(R.id.charwizSpinner)).getSelectedItemPosition());
        String charwizIndustryName = industries.get(((Spinner) findViewById(R.id.charwizSpinner)).getSelectedItemPosition());
        try {
            jsonObjectIndustry.put("industryID", charwizIndustryID);
            jsonObjectIndustry.put("industryName", charwizIndustryName);

            jsonObject.put("charity", UserData.getInstance().getId());
            jsonObject.put("listingTitle", charwizTitle);
            jsonObject.put("listingDescription", charwizDescription);
            jsonObject.put("location", charwizLocation);
            jsonObject.put("industry", jsonObjectIndustry);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        DBSystemNetwork.sendPostRequest("listing/new/", jsonObject, new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.hasStatusSuccessful()) {

                    Intent intent = new Intent(CharityWizard.this, SimpleResultActivity.class);
                    ResultData resultData = new ResultData("Listing Added", "");
                    resultData.putToIntent(intent);
                    startActivity(intent);
                } else {
                    Toast.makeText(CharityWizard.this, response.getErrorMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        System.out.println("REQUEST IS LPO: " + jsonObject.toString());

    }

    public void setupSpinner(){
        DBSystemNetwork.sendGetRequest("/listing/industries", new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.isConnectionSuccessful()) {
                    try {
                        industries = new ArrayList<>();
                        indices = new ArrayList<>();

                        for (int i = 0; i < response.getJsonObject().getJSONArray("body").length(); i++) {
                            String obj = response.getJsonObject().getJSONArray("body").getJSONObject(i).getString("industryName");
                            Integer index = response.getJsonObject().getJSONArray("body").getJSONObject(i).getInt("industryID");
                            industries.add(obj);
                            indices.add(index);
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CharityWizard.this,
                                android.R.layout.simple_spinner_item,industries);
                        spinner = (Spinner) findViewById(R.id.charwizSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        //spinner.setOnItemSelectedListener(this);

                    } catch (JSONException e) {
                        System.out.println(e);
                    }

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "day/month/year: " + dayOfMonth + "/" + month + "/" + year;
        dateText.setText(date);
    }
}

