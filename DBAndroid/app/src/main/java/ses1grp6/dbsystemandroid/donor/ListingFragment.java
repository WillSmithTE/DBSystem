package ses1grp6.dbsystemandroid.donor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class ListingFragment extends Fragment implements ListingAdapter.ItemClickListener {
    Context context;
    View rootView;
    ListingAdapter adapter;
    ArrayList<Listing> listingCharities;

    public ListingFragment()  {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        rootView = inflater.inflate(R.layout.fragment_listing_list, container, false);
        arrayBuild();
        return rootView;
    }

    private void arrayBuild(){
        DBSystemNetwork.sendGetRequest("listing/", new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.isConnectionSuccessful()) {
                    try {
                        listingCharities = new ArrayList<>();
                        int max = response.getJsonObject().getJSONArray("body").length();
                        if (max > 20) max = 20;
                        for (int i = 0; i < max; i++) {
                            JSONObject obj = response.getJsonObject().getJSONArray("body").getJSONObject(i);
                            listingCharities.add(new Listing(obj));
                        }
                        buildRecyclerView(listingCharities);
                    } catch (JSONException | ParseException e) {
                        System.out.println(e);
                    }

                } else {
                    Toast.makeText(context, response.message,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void buildRecyclerView(ArrayList<Listing> listingCharities){
        // Setting up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listingCharitiesRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new ListingAdapter(listingCharities);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int id) {
        String s = "Id " + id + " has been clicked";
//        Toast.makeText(context, s,
//                Toast.LENGTH_LONG).show();
//        System.out.println(s);
//
//        Intent intent = new Intent(getActivity(), ProfileActivity.class);
//        ListingCharity donor = listingCharities.get(0);
//        for (ListingCharity d : donors){
//            if (d.getId() == id) {
//                donor = d;
//            }
//        }
//        intent.putExtra("donor", donor);
//        startActivity(intent);
    }
}