package ses1grp6.dbsystemandroid.charity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.donor.ApplicantCardFragment;
import ses1grp6.dbsystemandroid.model.Application;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class EditListingFragment extends Fragment {

    private Listing listing;

    public EditListingFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        listing = Listing.getFromIntent(intent);
        fetchApplicants();
    }

    private void addApplicants(List<Application> applications) {
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();

        for (Application appli : applications) {
            ApplicantCardFragment fragApplicant = new ApplicantCardFragment();
            fragApplicant.setApplication(appli);
            fragTransaction.add(R.id.listingLayout, fragApplicant);
        }
        fragTransaction.commit();
    }

    private void fetchApplicants() {
        System.out.println("\"listing/applications/\" + listing.getId(): " + "listing/applications/" + listing.getId());
        DBSystemNetwork.sendGetRequest("listing/applications/" + listing.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.hasStatusSuccessful()) {

                    try {
                        JSONArray jsonDonors = response.getBodyJsonArray();
                        List<Application> applications = new ArrayList<>(jsonDonors.length());
                        
                        for (int i = 0; i < jsonDonors.length(); i++) {
                            applications.add(new Application(jsonDonors.getJSONObject(i)));
                        }
                        addApplicants(applications);
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "Unable to read applications from response", Toast.LENGTH_LONG).show();
                        System.err.println(response.getErrorMessage());
                    }
                } else {
                    Toast.makeText(getContext(), "Unable to get applications from server", Toast.LENGTH_LONG).show();
                    System.err.println(response.getErrorMessage());
                }
            }
        });
    }
}
