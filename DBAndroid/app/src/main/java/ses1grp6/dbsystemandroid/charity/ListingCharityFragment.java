package ses1grp6.dbsystemandroid.charity;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class ListingCharityFragment extends Fragment implements ListingCharitiesAdapter.ItemClickListener {
    Context context;
    View rootView;
    ListingCharitiesAdapter adapter;
    ArrayList<ListingCharity> listingCharities;

    public ListingCharityFragment()  {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        rootView = inflater.inflate(R.layout.fragment_listing_charities_list, container, false);
        arrayBuild();
        return rootView;
    }

    private void arrayBuild(){
        DBSystemNetwork.sendGetRequest(context, "listing/charity/1", new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.isConnectionSuccessful()) {

                    try {
                        listingCharities = new ArrayList<>();
                        System.out.println("REACHED oncreateivew listingcharities" + response.data);
                        for (int i = 0; i < response.getJsonObject().getJSONArray("body").length(); i++) {
                            JSONObject obj = response.getJsonObject().getJSONArray("body").getJSONObject(i);
                            listingCharities.add(new ListingCharity(obj));
                        }
                        buildRecyclerView(listingCharities);
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

    private void buildRecyclerView(ArrayList<ListingCharity> listingCharities){
        // Setting up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listingCharitiesRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new ListingCharitiesAdapter(listingCharities);
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
