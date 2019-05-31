package ses1grp6.dbsystemandroid.donor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ses1grp6.dbsystemandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplyListingFragment extends Fragment {

    private Button applyButton;
    private Button callButton;
    private Button seeLocationButton;

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
