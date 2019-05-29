package ses1grp6.dbsystemandroid.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Application;

public class ApplicationActivity extends AppCompatActivity {

    private TextView titleText;
    private TextView coverLetterText;
    private TextView contactNumberText;
    private TextView timestampText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        titleText = findViewById(R.id.appliTitle);
        coverLetterText = findViewById(R.id.appliCoverLetter);
        contactNumberText = findViewById(R.id.appliContactNumber);
        timestampText = findViewById(R.id.appliTimestamp);

        Intent intent = getIntent();
        Application application = Application.getFromIntent(intent);

        titleText.setText(getString(R.string.prefix_application_title) + "\"" + application.getCharity().getName() + "\"");
        coverLetterText.setText(application.getCoverLetter());
        checkAndSetText(timestampText, application.hasTimestamp(), getString(R.string.prefix_created_at) + application.getFormattedCreatedAt());
        checkAndSetText(contactNumberText, application.hasContactNumber(), application.getContactNumber());
    }

    private void checkAndSetText(TextView view, boolean check, String text) {

        if (check) {
            view.setText(text);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
