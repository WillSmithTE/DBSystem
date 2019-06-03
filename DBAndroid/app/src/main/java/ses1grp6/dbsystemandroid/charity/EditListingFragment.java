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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import ses1grp6.dbsystemandroid.DashboardActivity;
import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.donor.ApplicantCardFragment;
import ses1grp6.dbsystemandroid.model.Application;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.simpleResult.ResultData;
import ses1grp6.dbsystemandroid.util.simpleResult.SimpleResultActivity;

public class EditListingFragment extends Fragment {

    private View view;
    private Listing listing;
    private List<Fragment> cards = new LinkedList<>();
    private Button closeListingBtn;

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
        closeListingBtn = view.findViewById(R.id.closeListingBtn);

        closeListingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Close Listing")
                        .setMessage(getString(R.string.close_listing_confirmation))
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                closeListing();
                            }
                        })
                        .show();
            }
        });
        return view;
    }

    private void closeListing() {

        try {
            JSONObject jsonObject = listing.getJson();
            jsonObject.put("listingTitle","CLOSED#" + listing.getListingTitle());
            DBSystemNetwork.sendPutRequest(getActivity(), "/listing/", jsonObject, new DBSystemNetwork.OnRequestComplete() {
                @Override
                public void onRequestCompleted(RequestResponse response) {
                    if (response.hasStatusSuccessful()) {
                        Intent intent = new Intent(getContext(), SimpleResultActivity.class);
                        ResultData resultData = new ResultData("Closed the Listing!", "");
                        resultData.setBackActivity(DashboardActivity.class, "Back To Dashboard");
                        resultData.putToIntent(intent);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Could not tell the server to close listing", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (JSONException e) {
            Toast.makeText(getContext(), "Could not create a message to server to close listing", Toast.LENGTH_LONG).show();
        }
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
