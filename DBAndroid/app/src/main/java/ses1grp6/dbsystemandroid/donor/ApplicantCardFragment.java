package ses1grp6.dbsystemandroid.donor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Application;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicantCardFragment extends Fragment {

    private Application application;
    private TextView applicantNameText;

    public ApplicantCardFragment() {
        // Required empty public constructor
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applicant_card, container, false);
        if (application == null) throw new RuntimeException("ApplicationCardFragment.setApplication needs to be called first before this fragment can be used");
        applicantNameText = view.findViewById(R.id.applicantName);
        applicantNameText.setText(application.getDonor().getName());
        return view;
    }

}
