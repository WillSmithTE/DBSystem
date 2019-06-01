package ses1grp6.dbsystemandroid.donor;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Listing;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplyListingFragment extends Fragment {

    private Button applyButton;
    private Button callButton;
    private Button seeLocationButton;
    private Listing listing;

    public ApplyListingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_listing, container, false);

        callButton = view.findViewById(R.id.listingCallButton);
        seeLocationButton = view.findViewById(R.id.listingSeeLocation);
        applyButton = view.findViewById(R.id.listingApplyButton);
        Intent intent = getActivity().getIntent();
        listing = Listing.getFromIntent(intent);
        setApplyListener();
        setCallListener();
        setSeeLocationListener();
        return view;
    }

    private void setApplyListener() {
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setCallListener() {
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + listing.getContactNumber()));
                startActivity(intent);
            }
        });
    }

    private void setSeeLocationListener() {
        seeLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
