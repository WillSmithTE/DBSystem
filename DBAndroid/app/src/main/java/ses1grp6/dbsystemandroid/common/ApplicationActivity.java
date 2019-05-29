package ses1grp6.dbsystemandroid.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.donor.model.Application;

public class ApplicationActivity extends AppCompatActivity {

    private TextView title;
    private TextView coverLetter;
    private TextView contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        title = findViewById(R.id.appliTitle);
        coverLetter = findViewById(R.id.appliCoverLetter);
        contactNumber = findViewById(R.id.appliContactNumber);

        Intent intent = getIntent();
        Application application = Application.getFromIntent(intent);

        title.setText("For -- " + application.getCharity().getName());
        coverLetter.setText(application.getCoverLetter());
        contactNumber.setText(application.getContactNumber());
    }
}
