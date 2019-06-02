package ses1grp6.dbsystemandroid.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.util.FragBundler;

public class ListingActivity extends AppCompatActivity {

    private TextView titleText;
    private TextView descriptionText;
    private TextView listingOwner;
    private TextView industryText;
    private TextView expiryText;
    private TextView startDateText;
    private TextView endDateText;
    private TextView locationText;
    private TextView createdAtText;
    private TextView contactText;
    private LinearLayout eventDateBox;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        titleText = findViewById(R.id.listingTitle);
        descriptionText = findViewById(R.id.listingDescription);
        industryText = findViewById(R.id.listingIndustry);
        createdAtText = findViewById(R.id.listingCreatedAt);
        expiryText = findViewById(R.id.listingExpiry);
        startDateText = findViewById(R.id.listingStartDate);
        endDateText = findViewById(R.id.listingEndDate);
        locationText = findViewById(R.id.listingLocation);
        listingOwner = findViewById(R.id.listingOwner);
        eventDateBox = findViewById(R.id.listingEventDateBox);
        layout = findViewById(R.id.listingLayout);
        contactText = findViewById(R.id.listingContactNumber);

        Intent intent = getIntent();
        setupTextViews(intent);
        createDynamicFragment(intent);
    }

    private void setupTextViews(Intent intent) {
        Listing listing = Listing.getFromIntent(intent);

        SpannableString industryStr = new SpannableString(getString(R.string.prefix_location) + " " + listing.getLocation());
        industryStr.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)),
                0, getString(R.string.prefix_location).length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        titleText.setText(listing.getListingTitle());
        setStyledText(listingOwner, getString(R.string.prefix_by_name), listing.getCharity().getName());
        descriptionText.setText(listing.getListingDescription());
        setStyledText(createdAtText, getString(R.string.prefix_created_at), listing.getFormattedCreatedAt());

        if (listing.hasEventStartDate() && listing.hasEventEndDate()) {
            startDateText.setText(listing.getFormattedStartDate());
            endDateText.setText(listing.getFormattedEndDate());
        } else {
            layout.removeView(eventDateBox);
        }

        if (listing.hasExpiresAt())
            setStyledText(expiryText, getString(R.string.prefix_expiry), listing.getFormattedExpiresAt());
        else
            layout.removeView(expiryText);

        if (listing.hasIndustry())
            setStyledText(industryText, getString(R.string.prefix_industry), listing.getIndustry().getIndustryName());
        else
            layout.removeView(industryText);

        if (listing.hasLocation())
            setStyledText(locationText, getString(R.string.prefix_location), listing.getLocation());
        else
            layout.removeView(locationText);

        if (listing.hasContactNumber())
            setStyledText(contactText, getString(R.string.prefix_location), listing.getContactNumber());
        else
            layout.removeView(contactText);
    }

    private void createDynamicFragment(Intent intent) {
        FragBundler fragBundler = new FragBundler(intent);

        if (fragBundler.hasFragment()) {
            fragBundler.replaceWithFragment(getSupportFragmentManager(), R.id.listingFragContainer);
        }
    }

    private void setStyledText(TextView text, String prefix, String s) {
        String fullStr = prefix + " " + s;
        SpannableString span = new SpannableString(fullStr);
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)),
                prefix.length(), fullStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(span);
    }
}
