package ses1grp6.dbsystemandroid.charity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.UserData;
import ses1grp6.dbsystemandroid.UserType;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;


public class CharityProfileFragment extends Fragment {
    TextView charityProfileId, charityProfileName, charityProfileEmail, charityProfileSize, charityProfileContactNumber, charityProfileDescription;

    public CharityProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_charity_profile, container, false);

        charityProfileId = (TextView) view.findViewById(R.id.charityProfileId);
        charityProfileName = (TextView) view.findViewById(R.id.charityProfileName);
        charityProfileSize = (TextView) view.findViewById(R.id.charityProfileSize);
        charityProfileEmail = (TextView) view.findViewById(R.id.charityProfileEmail);
        charityProfileContactNumber = (TextView) view.findViewById(R.id.charityProfileContactNumber);
        charityProfileDescription = (TextView) view.findViewById(R.id.charityProfileDescription);

        buildProfile();

        return view;
    }

    private void buildProfile(){
        final int Id = UserData.getInstance().getId();

        DBSystemNetwork.sendGetRequest("/charity/" + Id, new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.isConnectionSuccessful()) {
                    try {
                        System.out.println("message " + response.getBodyJsonObject().getString("id"));
                        charityProfileId.setText(response.getBodyJsonObject().getString("id"));
                        charityProfileName.setText(response.getBodyJsonObject().getString("name"));
                        charityProfileContactNumber.setText(response.getBodyJsonObject().getString("contactNumber"));
                        charityProfileEmail.setText(response.getBodyJsonObject().getString("email"));
                        charityProfileSize.setText(response.getBodyJsonObject().getString("charitySize"));
                        charityProfileDescription.setText(response.getBodyJsonObject().getString("charity_description"));


                    } catch (JSONException e) {
                        System.out.println(e);
                    }

                }
            }
        });
    }

}
