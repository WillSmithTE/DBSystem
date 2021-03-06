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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.common.ListingActivity;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.util.SimpleRecyclerAdaptor;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class CharityHistoryFragment extends Fragment implements SimpleRecyclerAdaptor.Binder<CharityHistoryFragment.HistoryHolder> {

    List<Listing> fullHistory = new ArrayList<>();
    List<Listing> history = new ArrayList<>();
    SimpleRecyclerAdaptor<HistoryHolder, Listing> adapter;
    SearchView searchView;

    public CharityHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        searchView = rootView.findViewById(R.id.searchHistory);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        buildRecyclerView(rootView);
        return rootView;
    }

    private void search() {
        List<Listing> newHistory = new ArrayList<>();

        if (searchView.getQuery().toString().equals("")) {
            history.clear();
            history.addAll(fullHistory);
        } else {
            for (Listing listing : fullHistory) {

                if (listing.search(searchView.getQuery().toString().toLowerCase())) {
                    newHistory.add(listing);
                }
            }
            history.clear();
            history.addAll(newHistory);
        }
        adapter.notifyDataSetChanged();
    }

    private void buildRecyclerView(View rootView) {
        // Setup recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.historyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new SimpleRecyclerAdaptor<>(HistoryHolder.class, this, R.layout.charity_history_item, history);
        adapter.setOnItemClickListener(new SimpleRecyclerAdaptor.OnItemClickListener<Listing>() {
            @Override
            public void onClick(View view, Listing dataSet) {
                Intent intent = new Intent(getContext(), ListingActivity.class);
                dataSet.removeExpiryAt(); // We don't want to display expiry date.
                dataSet.putToIntent(intent);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        fetchCharityHistory();
    }

    private void fetchCharityHistory() {
        UserData userData = UserData.getInstance();
        DBSystemNetwork.sendGetRequest(getActivity(), "/charity/history/" + userData.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.hasStatusSuccessful()) {
                    Date currentDate = new Date();
                    JSONArray dataArray = response.getBodyJsonArray();

                    for (int i = 0; i < dataArray.length(); i++) {
                        Listing historyData;

                        try {
                            JSONObject jsonObject = dataArray.getJSONObject(i);
                            historyData = new Listing(jsonObject);
                        } catch (JSONException e) {
                            System.err.println("Found corrupted charity history data!");
                            continue;
                        }

                        if (historyData.hasExpired()) {
                            fullHistory.add(historyData);
                            history.add(historyData);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    System.out.println("Charity History Data Set Updated");
                } else {
                    Toast.makeText(getContext(), "Could not receive proper message from server.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder viewHolder, int i) {
        Listing hist = history.get(i);
        viewHolder.title.setText(hist.getListingTitle());
        String descip = hist.getListingDescription().substring(0, Math.min(hist.getListingDescription().length(), 90));
        viewHolder.description.setText(descip + ".....");
        viewHolder.industry.setText(getString(R.string.prefix_industry) + " " + hist.getIndustry());
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder {

        public final TextView title, description, industry;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.charityHistoryTitle);
            description = itemView.findViewById(R.id.charityHistoryDescrip);
            industry = itemView.findViewById(R.id.charityHistoryIndustry);
        }
    }
}
