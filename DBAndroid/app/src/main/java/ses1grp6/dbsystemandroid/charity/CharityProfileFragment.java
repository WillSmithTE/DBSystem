package ses1grp6.dbsystemandroid.charity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ses1grp6.dbsystemandroid.R;


public class CharityProfileFragment extends Fragment {


    public CharityProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charity_profile, container, false);
    }

}
