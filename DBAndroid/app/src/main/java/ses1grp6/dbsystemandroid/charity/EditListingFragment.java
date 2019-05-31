package ses1grp6.dbsystemandroid.charity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.common.ApplicationActivity;
import ses1grp6.dbsystemandroid.model.Application;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.FragBundler;
import ses1grp6.dbsystemandroid.util.SimpleRecyclerAdaptor;

public class EditListingFragment extends Fragment {

    private List<Application> applications;
    private Listing listing;
    private SimpleRecyclerAdaptor<ApplicationHolder, Application> adaptor;
    private RecyclerView recyclerView;

    public EditListingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_listing, container, false);
        recyclerView = view.findViewById(R.id.applicantsRecyclerView);
        FragBundler bundler = new FragBundler(getActivity().getIntent());
        listing = bundler.getModel();
        fetchApplicants();
        buildRecyclerView();
        return view;
    }

    private void fetchApplicants() {
        DBSystemNetwork.sendGetRequest("listing/applications/" + listing.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.hasStatusSuccessful()) {

                    try {
                        JSONArray jsonDonors = response.getBodyJsonArray();
                        applications = new ArrayList<>(jsonDonors.length());

                        for (int i = 0; i < jsonDonors.length(); i++) {
                            applications.add(new Application(jsonDonors.getJSONObject(i)));
                        }
                        adaptor.notifyDataSetChanged();
                    } catch (JSONException | ParseException e) {
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

    public void buildRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adaptor = new SimpleRecyclerAdaptor<>(ApplicationHolder.class, new Binder(), R.layout.card_application, applications);
        recyclerView.setAdapter(adaptor);
        adaptor.setOnItemClickListener(new SimpleRecyclerAdaptor.OnItemClickListener<Application>() {
            @Override
            public void onClick(View view, Application dataSet) {
                Intent intent = new Intent(getContext(), ApplicationActivity.class);
                dataSet.putToIntent(intent);
                startActivity(intent);
            }
        });
    }

    private class Binder implements SimpleRecyclerAdaptor.Binder<ApplicationHolder> {
        @Override
        public void onBindViewHolder(@NonNull ApplicationHolder viewHolder, int i) {
            Application application = applications.get(i);
            viewHolder.applicantName.setText(application.getDonor().getName());
        }
    }

    private static class ApplicationHolder extends RecyclerView.ViewHolder {

        public final TextView applicantName;

        public ApplicationHolder(@NonNull View itemView) {
            super(itemView);
            applicantName = itemView.findViewById(R.id.applicantName);
        }
    }
}
