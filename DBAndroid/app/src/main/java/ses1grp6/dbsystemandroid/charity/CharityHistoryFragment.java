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
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.common.ListingActivity;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.util.SimpleRecyclerAdaptor;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class CharityHistoryFragment extends Fragment implements SimpleRecyclerAdaptor.Binder<CharityHistoryFragment.HistoryHolder> {

    List<CharityHistory> history = new ArrayList<>();
    SimpleRecyclerAdaptor<HistoryHolder, CharityHistory> adapter;

    public CharityHistoryFragment() {
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
        // TODO REMOVE Sample/Test data
        history.add(new CharityHistory("Some Title", new Date(), "99 Some Street, Jakarta", "Some kind of transaction was performed", "IT"));
        // TODO END

        // Setup recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.historyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new SimpleRecyclerAdaptor<>(HistoryHolder.class, this, R.layout.card_charity_history, history);
        adapter.setOnItemClickListener(new SimpleRecyclerAdaptor.OnItemClickListener<Listing>() {
            @Override
            public void onClick(View view, Listing dataSet) {
                Intent intent = new Intent(getContext(), ListingActivity.class);
                dataSet.putToIntent(intent);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        fetchCharityHistory();
    }

    private void fetchCharityHistory() {
        UserData userData = UserData.getInstance();
        DBSystemNetwork.sendGetRequest("/charity/history/" + userData.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (response.hasStatusSuccessful()) {
                    JSONArray dataArray = response.getBodyJsonArray();

                    for (int i = 0; i < dataArray.length(); i++) {
                        CharityHistory historyData;

                        try {
                            JSONObject jsonObject = dataArray.getJSONObject(i);
                            historyData = new CharityHistory(jsonObject);
                        } catch (JSONException | ParseException e) {
                            System.err.println("Found corrupted charity history data!");
                            continue;
                        }
                        history.add(historyData);
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
        CharityHistory hist = history.get(i);
        viewHolder.title.setText(hist.title);
        viewHolder.date.setText(new SimpleDateFormat("d MMM y", Locale.getDefault()).format(hist.date) + " "); // Hacky fix to cut off text by adding space.
        viewHolder.address.setText(getString(R.string.prefix_location) + hist.address);
        String descip = hist.description.substring(0, Math.min(hist.description.length(), 90));
        viewHolder.description.setText(descip + ".....");
        viewHolder.industry.setText(getString(R.string.prefix_industry) + hist.industry);
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder {

        public final TextView title, date, address, description, industry;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.charityHistoryTitle);
            date = itemView.findViewById(R.id.charityHistoryTime);
            address = itemView.findViewById(R.id.charityHistoryAddress);
            description = itemView.findViewById(R.id.charityHistoryDescrip);
            industry = itemView.findViewById(R.id.charityHistoryIndustry);
        }
    }
}
