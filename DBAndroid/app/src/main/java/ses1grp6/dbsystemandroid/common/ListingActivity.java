package ses1grp6.dbsystemandroid.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.charity.model.Listing;

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
        Listing listing = Listing.getFromIntent(intent);

        titleText.setText(listing.getListingTitle());
        descriptionText.setText(listing.getListingDescription());
        industryText.setText(listing.getIndustry());
        locationText.setText(listing.getLocation());
    }
}
