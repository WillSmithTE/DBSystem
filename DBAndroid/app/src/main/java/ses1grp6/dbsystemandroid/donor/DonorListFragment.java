package ses1grp6.dbsystemandroid.donor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import ses1grp6.dbsystemandroid.LoginActivity;
import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class DonorListFragment extends Fragment implements DonorsAdapter.ItemClickListener {
    Context context;
    View rootView;
    DonorsAdapter adapter;
    ArrayList<Donor> donors;

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
                        donors = new ArrayList<>();
                        for (int i = 0; i < response.getJsonArray().length(); i++) {
                            JSONObject obj = response.getJsonArray().getJSONObject(i);
                            donors.add(new Donor(obj.getInt("id"), obj.getString("name"), obj.getString("email"), obj.getString("contactNumber")));
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
        adapter = new DonorsAdapter(donors);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int id) {
        String s = "Id " + id + " has been clicked";
        Toast.makeText(context, s,
                Toast.LENGTH_LONG).show();
        System.out.println(s);

        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        Donor donor = donors.get(0);
        for (Donor d : donors){
            if (d.getId() == id) {
                donor = d;
            }
        }
        intent.putExtra("donor", donor);
        startActivity(intent);
    }
}
