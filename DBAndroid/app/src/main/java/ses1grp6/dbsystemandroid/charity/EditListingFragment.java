package ses1grp6.dbsystemandroid.charity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
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

    private void addApplicants(final List<Application> applications) {
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();

        for (int i = 0; i < applications.size(); i++) {
            ApplicantCardFragment fragApplicant = new ApplicantCardFragment();
            fragApplicant.setupCard(applications.get(i), i, new ApplicantCardFragment.OnApplicantClicked() {
                @Override
                public void onClick(View view, final ApplicantCardFragment frag, int index, boolean accepted) {

                    if (accepted) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Accept Applicant")
                                .setMessage(getString(R.string.applicant_accept_confirmation))
                                .setNegativeButton(android.R.string.no, null)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        acceptApplicant(frag, applications.get(i));
                                    }
                                })
                                .show();
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Reject Applicant")
                                .setMessage(getString(R.string.applicant_reject_confirmation))
                                .setNegativeButton(android.R.string.no, null)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        rejectApplicant(frag, applications.get(i));
                                    }
                                })
                                .show();
                    }
                }
            });
            fragTransaction.add(R.id.listingLayout, fragApplicant);
        }
        fragTransaction.commit();
    }

    private void rejectApplicant(final ApplicantCardFragment frag, Application application) {
        DBSystemNetwork.sendGetRequest(getActivity(), "application/reject/" + application.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (!response.hasStatusSuccessful()) {
                    Toast.makeText(getContext(), "Unable tell server to reject application", Toast.LENGTH_LONG).show();
                    System.err.println(response.getErrorMessage());
                } else {
                    removeApplicant(frag);
                }
            }
        });
    }

    private void acceptApplicant(final ApplicantCardFragment frag, Application application) {
        DBSystemNetwork.sendGetRequest(getActivity(), "application/" + application.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (!response.hasStatusSuccessful()) {
                    Toast.makeText(getContext(), "Unable tell server to accept application", Toast.LENGTH_LONG).show();
                    System.err.println(response.getErrorMessage());
                } else {
                    removeApplicant(frag);
                }
            }
        });
    }

    private void removeApplicant(Fragment frag) {
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.remove(frag);
        fragTransaction.commit();
    }

    private void fetchApplicants() {
        System.out.println("\"listing/applications/\" + listing.getId(): " + "listing/applications/" + listing.getId());
        DBSystemNetwork.sendGetRequest(getActivity(), "listing/applications/" + listing.getId(), new DBSystemNetwork.OnRequestComplete() {
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
