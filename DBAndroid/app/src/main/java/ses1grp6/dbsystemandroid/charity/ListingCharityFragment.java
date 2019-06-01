package ses1grp6.dbsystemandroid.charity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Listing;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.FragBundler;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.common.ListingActivity;

public class ListingCharityFragment extends Fragment implements ListingCharitiesAdapter.ItemClickListener {
    Context context;
    View rootView;
    ListingCharitiesAdapter adapter;
    ArrayList<Listing> listingCharities;

    public ListingCharityFragment()  {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        rootView = inflater.inflate(R.layout.fragment_listing_charities_list, container, false);

        Button button = (Button) rootView.findViewById(R.id.button_create_listing);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, CharityWizard.class);
                startActivity(intent);
            }
        });

        arrayBuild();
        return rootView;
    }

    private void arrayBuild(){
        DBSystemNetwork.sendGetRequest(getActivity(), "listing/charity/" + UserData.getInstance().getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.isConnectionSuccessful()) {

                    try {
                        listingCharities = new ArrayList<>();
                        System.out.println("REACHED oncreateivew listingcharities" + response.data);
                        for (int i = 0; i < response.getJsonObject().getJSONArray("body").length(); i++) {
                            JSONObject obj = response.getJsonObject().getJSONArray("body").getJSONObject(i);
                            listingCharities.add(new Listing(obj));
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

    private void buildRecyclerView(ArrayList<Listing> listingCharities){
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
    public void onItemClick(View view, int id, Listing listing) {
        String s = "Id " + id + " has been clicked";
        System.out.println(s + listing.getListingTitle());

        Intent intent = new Intent(getContext(), ListingActivity.class);
        FragBundler fragBundler = new FragBundler(intent);
        fragBundler.putToIntent(EditListingFragment.class);
        listing.putToIntent(intent);
        startActivity(intent);
    }
}
