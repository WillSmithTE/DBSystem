package ses1grp6.dbsystemandroid.charity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ses1grp6.dbsystemandroid.DashboardActivity;
import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Charity;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.util.simpleResult.ResultData;
import ses1grp6.dbsystemandroid.util.simpleResult.SimpleResultActivity;

public class CharityWizard extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private ArrayList<String> industries;
    private ArrayList<Integer> indices;
    private TextView startDateText, expiryText;
    private Button startDateBtn, expiryDateBtn;
    private Calendar c;
    private DatePickerDialog dpd;
    private TextView endDateText;
    private Button endDateBtn;
    private Calendar ce;
    private DatePickerDialog edpd;
    private Date startDate, endDate, expiryDate;
    private EditText titleInput, descripInput, locationInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spinner = (Spinner) findViewById(R.id.charwizSpinner);
        setupSpinner();
        setContentView(R.layout.activity_charity_wizard);

        endDateText = (TextView) findViewById(R.id.charwizEndDateText);
        endDateBtn = (Button) findViewById(R.id.charwizEndDateBtn);

        startDateText = (TextView) findViewById(R.id.charwizstartDateText);
        startDateBtn = (Button) findViewById(R.id.charwizstartDateBtn);

        expiryDateBtn = findViewById(R.id.charwizexpiryDateBtn);
        expiryText = findViewById(R.id.charwizexpiryDateText);

        titleInput = findViewById(R.id.charwizTitle);
        descripInput = findViewById(R.id.charwizDescription);
        locationInput = findViewById(R.id.charwizLocationInput);

        setupExpiryDate();

        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(CharityWizard.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        startDateText.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                        startDate = new GregorianCalendar(mYear, mMonth, mDay).getTime();
                    }
                }, year, month, day);
            dpd.show();
            }
        });

       endDateBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ce = Calendar.getInstance();
               int day = ce.get(Calendar.DAY_OF_MONTH);
               int month = ce.get(Calendar.MONTH);
               int year = ce.get(Calendar.YEAR);

               edpd = new DatePickerDialog(CharityWizard.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        endDateText.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                        endDate = new GregorianCalendar(mYear, mMonth, mDay).getTime();
                   }
               }, year, month, day);
               edpd.show();
           }
       });
    }

    private void setupExpiryDate() {
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        expiryDate = new GregorianCalendar(year, month, day + 1).getTime();
        expiryText.setText((day + 1) + "/" + month + "/" + year);

        expiryDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ce = Calendar.getInstance();
                int day = ce.get(Calendar.DAY_OF_MONTH);
                int month = ce.get(Calendar.MONTH);
                int year = ce.get(Calendar.YEAR);

                edpd = new DatePickerDialog(CharityWizard.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        expiryText.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                        expiryDate = new GregorianCalendar(mYear, mMonth, mDay).getTime();
                    }
                }, year, month, day);
                edpd.show();
            }
        });
    }

    private boolean checkEmpty() {
        return titleInput.getText().equals("") &&
                descripInput.getText().equals("");
    }

    private String formattedDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
    }

    public void onPostClick(View view) {
        if (checkEmpty()) return;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectIndustry = new JSONObject();
        String charwizTitle = (titleInput).getText().toString();
        String charwizDescription = descripInput.getText().toString();
        String charwizLocation = locationInput.getText().toString();
        int charwizIndustryID = indices.get(((Spinner) findViewById(R.id.charwizSpinner)).getSelectedItemPosition());
        String charwizIndustryName = industries.get(((Spinner) findViewById(R.id.charwizSpinner)).getSelectedItemPosition());

        try {
            jsonObjectIndustry.put("industryID", charwizIndustryID);
            jsonObjectIndustry.put("industryName", charwizIndustryName);

            jsonObject.put("charity", UserData.getInstance().getId());
            jsonObject.put("listingTitle", charwizTitle);
            jsonObject.put("listingDescription", charwizDescription);
            if (!charwizLocation.equals("")) jsonObject.put("location", charwizLocation);
            jsonObject.put("industry", jsonObjectIndustry);

            if (startDate != null && endDate != null) {
                jsonObject.put("eventStartDate", formattedDate(startDate));
                jsonObject.put("eventEndDate", formattedDate(endDate));
            }
            jsonObject.put("expiresAt", formattedDate(expiryDate));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        DBSystemNetwork.sendPostRequest(this, "listing/new/", jsonObject, new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.hasStatusSuccessful()) {

                    Intent intent = new Intent(CharityWizard.this, SimpleResultActivity.class);
                    ResultData resultData = new ResultData("Listing Added", "");
                    resultData.setBackActivity(DashboardActivity.class, "Go back to Dashboard");
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
        DBSystemNetwork.sendGetRequest(this, "/listing/industries", new DBSystemNetwork.OnRequestComplete() {
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
                                android.R.layout.simple_spinner_item, industries);
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

}
