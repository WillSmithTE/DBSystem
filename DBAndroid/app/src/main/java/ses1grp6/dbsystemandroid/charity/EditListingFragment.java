package ses1grp6.dbsystemandroid.charity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.donor.ApplicantCardFragment;
import ses1grp6.dbsystemandroid.model.Application;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class EditListingFragment extends Fragment {

    private View view;
    private Listing listing;
    private List<Fragment> cards = new LinkedList<>();

    public EditListingFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        listing = Listing.getFromIntent(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchApplicants();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_listing, container, false);
        return view;
    }

    private void addApplicantCards(final List<Application> applications) {
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();

        for (int i = 0; i < applications.size(); i++) {
            ApplicantCardFragment fragApplicant = new ApplicantCardFragment();
            fragApplicant.setupCard(applications.get(i));
            cards.add(fragApplicant);
            fragTransaction.add(R.id.listingLayout, fragApplicant);
        }
        fragTransaction.commit();
    }

    private void clearApplicants() {
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();

        for (Fragment f : cards) {
            fragTransaction.remove(f);
        }
        cards.clear();
        fragTransaction.commit();
    }

    private void fetchApplicants() {
        DBSystemNetwork.sendGetRequest(getActivity(), "listing/applications/" + listing.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.hasStatusSuccessful()) {

                    try {
                        clearApplicants();
                        JSONArray jsonDonors = response.getBodyJsonArray();
                        List<Application> applications = new ArrayList<>(jsonDonors.length());

                        for (int i = 0; i < jsonDonors.length(); i++) {
                            Application application = new Application(jsonDonors.getJSONObject(i));

                            if (application.isPending()) {
                                applications.add(application);
                            }
                        }

                        if (applications.size() == 0) {
                            showApplicantsEmpty();
                        } else {
                            addApplicantCards(applications);
                        }
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

    private void showApplicantsEmpty() {
        TextView text = new TextView(getContext());
        text.setText("None");
        text.setGravity(Gravity.CENTER);
        text.setPadding(50, 50, 50, 50);
        ((ViewGroup)view).addView(text);
    }
}
