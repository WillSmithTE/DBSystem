package ses1grp6.dbsystemandroid.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Application;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.util.FragBundler;
import ses1grp6.dbsystemandroid.util.TxStyler;

public class ApplicationActivity extends AppCompatActivity {

    private TxStyler styler = TxStyler.getInstance();
    private TextView titleText;
    private TextView coverLetterText;
    private TextView createdAtText;
    private TextView charityByText;
    private TextView industryText;
    private TextView contactText;
    private LinearLayout applicationLayout;
    private LinearLayout listingLayout;
    private Application application;
    private Listing listing;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        titleText = findViewById(R.id.appliListingTitle);
        coverLetterText = findViewById(R.id.appliCoverLetter);
        createdAtText = findViewById(R.id.appliCreatedAt);
        charityByText = findViewById(R.id.appliBy);
        industryText = findViewById(R.id.appliIndustry);
        applicationLayout = findViewById(R.id.appliLayout);
        listingLayout = findViewById(R.id.listingLayout);
        contactText = findViewById(R.id.appliContact);

        intent = getIntent();
        application = Application.getFromIntent(intent);
        listing = application.getListing();
        setupTextViews();
        createDynamicFragment();
    }

    public void onViewListingClicked(View view) {
        Intent intent = new Intent(this, ListingActivity.class);
        listing.putToIntent(intent);
        startActivity(intent);
    }

    private void setupTextViews() {
        TxStyler titleStyler = new TxStyler(getResources().getColor(R.color.colorLinkable));
        titleStyler.prefix(titleText, getString(R.string.prefix_application_title), listing.getListingTitle());
        styler.prefix(charityByText, getString(R.string.prefix_by_name), application.getCharity().getName());
        coverLetterText.setText(application.getCoverLetter());

        if (application.hasCreatedAt())
            styler.prefix(createdAtText, getString(R.string.prefix_created_at), application.getFormattedCreatedAt());
        else
            applicationLayout.removeView(createdAtText);

        if (application.hasContactNumber())
            styler.prefix(contactText, getString(R.string.prefix_contact_number), application.getContactNumber());
        else
            applicationLayout.removeView(contactText);
        
        if (listing.hasIndustry())
            styler.prefix(industryText, getString(R.string.prefix_industry), listing.getIndustry().getIndustryName());
        else
            listingLayout.removeView(createdAtText);
    }

    private void createDynamicFragment() {
        FragBundler fragBundler = new FragBundler(intent);

        if (fragBundler.hasFragment()) {
            fragBundler.replaceWithFragment(getSupportFragmentManager(), R.id.appliFragContainer);
        }
    }
}
