package ses1grp6.dbsystemandroid.donor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ses1grp6.dbsystemandroid.R;

public class DonorProfileFragment extends Fragment {
    Context context;
    View rootView;

    public DonorProfileFragment()  {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        rootView = inflater.inflate(R.layout.donor_item, container, true);


        return rootView;
    }
}
