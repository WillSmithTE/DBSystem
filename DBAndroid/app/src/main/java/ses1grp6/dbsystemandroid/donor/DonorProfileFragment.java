package ses1grp6.dbsystemandroid.donor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;

import java.nio.file.attribute.UserDefinedFileAttributeView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;
import ses1grp6.dbsystemandroid.util.UserData;

public class DonorProfileFragment extends Fragment {
    View view;

    public DonorProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_donor_profile, container, false);

        buildProfile();

        return view;
    }

    public void buildProfile(){
        DBSystemNetwork.sendGetRequest("/donor/" + UserData.getInstance().getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {
                if (response.isConnectionSuccessful()) {
                    try {
                        ((TextView) view.findViewById(R.id.donor_profile_name)).setText(response.getBodyJsonObject().getString("name"));
                        ((TextView) view.findViewById(R.id.donor_profile_email)).setText(response.getBodyJsonObject().getString("email"));
                        ((TextView) view.findViewById(R.id.donor_profile_contactnumber)).setText(response.getBodyJsonObject().getString("contactNumber"));


                    } catch (JSONException e) {
                        System.out.println(e);
                    }

                }
            }
        });
    }


}
