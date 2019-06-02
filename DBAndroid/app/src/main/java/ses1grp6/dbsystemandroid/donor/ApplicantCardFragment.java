package ses1grp6.dbsystemandroid.donor;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.charity.ApplicantAcceptFragment;
import ses1grp6.dbsystemandroid.common.ApplicationActivity;
import ses1grp6.dbsystemandroid.model.Application;
import ses1grp6.dbsystemandroid.util.FragBundler;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicantCardFragment extends Fragment {

    private Application application;
    private TextView applicantNameText;

    public ApplicantCardFragment() {
        // Required empty public constructor
    }

    public void setupCard(Application application) {
        this.application = application;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applicant_card, container, false);
        if (application == null) throw new RuntimeException("ApplicationCardFragment.setupCard needs to be called first before this fragment can be used");
        applicantNameText = view.findViewById(R.id.applicantName);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ApplicationActivity.class);
                new FragBundler(intent).putToIntent(ApplicantAcceptFragment.class);
                application.putToIntent(intent);
                startActivityForResult(intent, 0);
            }
        });



        applicantNameText.setText(application.getDonor().getName());
        return view;
    }
}
