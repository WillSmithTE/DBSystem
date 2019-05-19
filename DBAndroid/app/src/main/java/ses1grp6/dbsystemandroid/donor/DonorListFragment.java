package ses1grp6.dbsystemandroid.donor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ses1grp6.dbsystemandroid.DashboardActivity;
import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class DonorListFragment extends Fragment {
    Context context;
    View rootView;

    public DonorListFragment()  {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        rootView = inflater.inflate(R.layout.fragment_donor_list, container, false);
        getDonors();
        return rootView;
    }

    private void getDonors(){
        DBSystemNetwork.sendGetRequest(context, "donor/", new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.isConnectionSuccessful()) {

                    try {
                        ArrayList<Donor> donors = new ArrayList<>();
                        for (int i = 0; i < response.getJsonArray().length(); i++) {
                            JSONObject obj = response.getJsonArray().getJSONObject(i);
                            donors.add(new Donor(obj.getString("name"), obj.getString("email"), obj.getString("contactNumber")));
                        }
                        buildRecyclerView(donors);
                    } catch (JSONException e) {
                        System.out.println(e);
                    }

                } else {
                    Toast.makeText(context, response.message,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void buildRecyclerView(ArrayList<Donor> donors){
        // Setting up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.donorRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        DonorsAdapter adapter = new DonorsAdapter(donors);
        recyclerView.setAdapter(adapter);
    }
}