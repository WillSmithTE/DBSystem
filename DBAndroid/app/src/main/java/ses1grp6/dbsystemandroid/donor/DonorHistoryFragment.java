package ses1grp6.dbsystemandroid.donor;


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
import java.util.HashSet;
import java.util.List;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.common.ApplicationActivity;
import ses1grp6.dbsystemandroid.common.ListingActivity;
import ses1grp6.dbsystemandroid.model.Application;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.util.SimpleRecyclerAdaptor;
import ses1grp6.dbsystemandroid.util.TxStyler;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorHistoryFragment extends Fragment implements SimpleRecyclerAdaptor.Binder<DonorHistoryFragment.HistoryHolder> {

    private List<Application> allHistory = new ArrayList<>();
    private List<Application> history = new ArrayList<>();
    private SimpleRecyclerAdaptor<HistoryHolder, Application> adapter;
    private HashSet<Integer> filter;
    private SearchView searchView;

    public DonorHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * @param filter The filter for accepted flag.
     */
    public void setFilter(HashSet<Integer> filter) {
        this.filter = filter;
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
        List<Application> newHistory = new ArrayList<>();

        if (searchView.getQuery().toString().equals("")) {
            history.clear();
            history.addAll(allHistory);
        } else {
            for (Application application : allHistory) {

                if (application.search(searchView.getQuery().toString().toLowerCase())) {
                    newHistory.add(application);
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
        adapter = new SimpleRecyclerAdaptor<>(HistoryHolder.class, this, R.layout.donor_history_item, history);
        adapter.setOnItemClickListener(new SimpleRecyclerAdaptor.OnItemClickListener<Application>() {
            @Override
            public void onClick(View view, Application dataSet) {
                Intent intent = new Intent(getContext(), ApplicationActivity.class);
                dataSet.putToIntent(intent);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        fetchDonorHistory();
    }

    private void fetchDonorHistory() {
        UserData userData = UserData.getInstance();
        DBSystemNetwork.sendGetRequest(getActivity(), "/donor/history/" + userData.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.hasStatusSuccessful()) {
                    JSONArray dataArray = response.getBodyJsonArray();

                    for (int i = 0; i < dataArray.length(); i++) {

                        try {
                            JSONObject jsonObject = dataArray.getJSONObject(i);
                            Application application = new Application(jsonObject);

                            if (filter.contains(application.getAccepted())) {
                                history.add(application);
                                allHistory.add(application);
                            }
                        } catch (JSONException e) {
                            System.err.println("Found corrupted donor history data!");
                        }
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
        Application hist = history.get(i);
        TxStyler titleStyler = new TxStyler(getResources().getColor(R.color.colorLinkable));
        titleStyler.prefix(viewHolder.title, getString(R.string.prefix_application_title), hist.getListing().getListingTitle());
        viewHolder.charityName.setText(getString(R.string.prefix_by_name) + " " + hist.getCharity().getName());
        String descip = hist.getCoverLetter().substring(0, Math.min(hist.getCoverLetter().length(), 90));
        viewHolder.description.setText(descip + ".....");
        viewHolder.industry.setText(getString(R.string.prefix_industry) + " " + hist.getListing().getIndustry().getIndustryName());
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder {

        public final TextView title, charityName, description, industry;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.donorHistoryTitle);
            charityName = itemView.findViewById(R.id.donorHistoryCharityName);
            description = itemView.findViewById(R.id.donorHistoryDescrip);
            industry = itemView.findViewById(R.id.donorHistoryIndustry);
        }
    }


}
