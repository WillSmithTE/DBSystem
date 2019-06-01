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

import java.time.Year;
import java.util.Calendar;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.simpleResult.ResultData;
import ses1grp6.dbsystemandroid.util.simpleResult.SimpleResultActivity;

public class charityWizard extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, DatePickerDialog.OnDateSetListener {

    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dateText = findViewById(R.id.dateshow);
        findViewById(R.id.charwizstartDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_wizard);

        Spinner spinner = findViewById(R.id.charwizSpinner); //ref to spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
        String charwizTitle = ((EditText) findViewById(R.id.charwizTitle)).getText().toString();
        String charwizDescription = ((EditText) findViewById(R.id.charwizDescription)).getText().toString();
        //String charwizDateTime = ((EditText) findViewById(R.id.charwizDateTime)).getText().toString();
        String charwizLocation = ((EditText) findViewById(R.id.charwizLocation)).getText().toString();
        //String charwizIndustry = ((EditText) findViewById(R.id.charwizIndustry)).getText().toString();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "day/month/year: " + dayOfMonth + "/" + month + "/" + year;
        dateText.setText(date);
    }
}
