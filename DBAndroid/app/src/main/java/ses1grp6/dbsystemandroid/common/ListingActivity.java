package ses1grp6.dbsystemandroid.common;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.util.FragBundler;

public class ListingActivity extends AppCompatActivity {

    private TextView titleText;
    private TextView descriptionText;
    private TextView industryText;
    private TextView expiryText;
    private TextView startDateText;
    private TextView endDateText;
    private TextView locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        titleText = findViewById(R.id.listingTitle);
        descriptionText = findViewById(R.id.listingDescription);
        industryText = findViewById(R.id.listingIndustry);
        expiryText = findViewById(R.id.listingExpiry);
        startDateText = findViewById(R.id.listingStartDate);
        endDateText = findViewById(R.id.listingEndDate);
        locationText = findViewById(R.id.listingLocation);

        Intent intent = getIntent();
        setupTextViews(intent);
        createDynamicFragment(intent);
    }

    private void setupTextViews(Intent intent) {
        Listing listing = Listing.getFromIntent(intent);

        titleText.setText(listing.getListingTitle());
        descriptionText.setText(listing.getListingDescription());
        checkAndSetText(startDateText, listing.hasEventStartDate(), listing.getFormattedStartDate());
        checkAndSetText(endDateText, listing.hasEventEndDate(), listing.getFormattedEndDate());
        checkAndSetText(expiryText, listing.hasExpiresAt(), listing.getFormattedExpiresAt());
        checkAndSetText(industryText, listing.hasIndustry(), getString(R.string.prefix_industry) + listing.getIndustry());
        checkAndSetText(locationText, listing.hasLocation(), getString(R.string.prefix_location) + listing.getLocation());
    }

    private void createDynamicFragment(Intent intent) {
        FragBundler fragBundler = new FragBundler(intent);

        if (fragBundler.hasFragment()) {
            fragBundler.replaceWithFragment(getSupportFragmentManager(), R.id.listingFragmentContainer);
        }
    }

    private void checkAndSetText(TextView view, boolean check, String text) {

        if (check) {
            view.setText(text);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
