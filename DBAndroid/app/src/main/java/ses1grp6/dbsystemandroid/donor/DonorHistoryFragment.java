package ses1grp6.dbsystemandroid.donor;


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
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.charity.model.Listing;
import ses1grp6.dbsystemandroid.util.SimpleRecyclerAdaptor;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorHistoryFragment extends Fragment implements SimpleRecyclerAdaptor.Binder<DonorHistoryFragment.HistoryHolder> {

    List<Listing> history = new ArrayList<>();
    SimpleRecyclerAdaptor<HistoryHolder, Listing> adapter;

    public DonorHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        buildRecyclerView(rootView);
        return rootView;
    }

    private void buildRecyclerView(View rootView) {
        // Setup recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.historyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new SimpleRecyclerAdaptor<>(HistoryHolder.class, this, R.layout.donor_history_card, history);
        recyclerView.setAdapter(adapter);
        fetchDonorHistory();
    }

    private void fetchDonorHistory() {
        UserData userData = UserData.getInstance();
        DBSystemNetwork.sendGetRequest("/donor/history/" + userData.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.hasStatusSuccessful()) {
                    JSONArray dataArray = response.getBodyJsonArray();

                    for (int i = 0; i < dataArray.length(); i++) {
                        Listing historyData;

                        try {
                            JSONObject jsonObject = dataArray.getJSONObject(i);
                            if (jsonObject.getInt("accepted") == 0) continue;
                            historyData = new Listing(jsonObject);
                        } catch (JSONException | ParseException e) {
                            System.err.println("Found corrupted donor history data!");
                            continue;
                        }
                        history.add(historyData);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Could not receive proper message from server.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder viewHolder, int i) {
        Listing hist = history.get(i);
        viewHolder.title.setText(getString(R.string.prefix_application_title) + "\"" + hist.getListingTitle() + "\"");
        viewHolder.time.setText(hist.getFormattedTimestamp()); // Hacky fix to cut off text by adding space.
        viewHolder.charityName.setText(getString(R.string.prefix_by_name) + hist.getCharity().getName());
        viewHolder.address.setText(getString(R.string.prefix_location) + hist.getLocation());
        String descip = hist.getListingDescription().substring(0, Math.min(hist.getListingDescription().length(), 90));
        viewHolder.description.setText(descip + ".....");
        viewHolder.industry.setText(getString(R.string.prefix_industry) + hist.getIndustry());
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder {

        public final TextView title, charityName, address, time, description, industry;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.donorHistoryTitle);
            time = itemView.findViewById(R.id.donorHistoryTime);
            charityName = itemView.findViewById(R.id.donorHistoryCharityName);
            address = itemView.findViewById(R.id.donorHistoryAddress);
            description = itemView.findViewById(R.id.donorHistoryDescrip);
            industry = itemView.findViewById(R.id.donorHistoryIndustry);
        }
    }


}
