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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.SimpleRecyclerAdaptor;
import ses1grp6.dbsystemandroid.UserData;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorHistoryFragment extends Fragment implements SimpleRecyclerAdaptor.Binder<DonorHistoryFragment.HistoryHolder> {

    List<DonorHistory> history = new ArrayList<>();
    SimpleRecyclerAdaptor<HistoryHolder, DonorHistory> adapter;

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
        // TODO REMOVE SAMPLE TEST DATA
        history.add(new DonorHistory("Save The Team", new Date(), "Help Master", "10 Lane, Brisbane", "An event intended to help teams that struggle. Bla bla bla, bla bLA bla.", "Science"));
        // TODO END

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
                        DonorHistory historyData;

                        try {
                            JSONObject jsonObject = dataArray.getJSONObject(i);
                            if (jsonObject.getInt("accepted") == 0) continue;
                            historyData = new DonorHistory(jsonObject);
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
        DonorHistory hist = history.get(i);
        viewHolder.title.setText("For  \"" + hist.title + "\"");
        viewHolder.time.setText(new SimpleDateFormat("d MMM Y", Locale.getDefault()).format(hist.date) + " "); // Hacky fix to cut off text by adding space.
        viewHolder.charityName.setText("By " + hist.charityName);
        viewHolder.address.setText("At " + hist.address);
        String descip = hist.description.substring(0, Math.min(hist.description.length(), 90));
        viewHolder.description.setText(descip + ".....");
        viewHolder.industry.setText("Under " + hist.industry);
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
