package ses1grp6.dbsystemandroid.donor;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Charity;
import ses1grp6.dbsystemandroid.model.Donor;
import ses1grp6.dbsystemandroid.model.Listing;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();

        if (Charity.hasInIntent(intent)) {
            Charity charity = Charity.getFromIntent(intent);
            ProfileFragment frag = new ProfileFragment();
            frag.setProfileModel(charity);
            fragTransaction.add(R.id.profileLayout, frag);
        } else if (Donor.hasInIntent(intent)) {
            Donor donor = Donor.getFromIntent(intent);
            ProfileFragment frag = new ProfileFragment();
            frag.setProfileModel(donor);
            fragTransaction.add(R.id.profileLayout, frag);
        } else {
            System.err.println("Could not find a model from intent!!!");
        }
        fragTransaction.commit();
    }
}
