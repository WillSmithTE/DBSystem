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
import ses1grp6.dbsystemandroid.model.Application;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicantCardFragment extends Fragment {

    private Application application;
    private TextView applicantNameText;
    private Button applicantAccept;
    private Button applicantDecline;
    private OnApplicantClicked listener;
    private int index;

    public ApplicantCardFragment() {
        // Required empty public constructor
    }

    public void setupCard(Application application, int index, OnApplicantClicked listener) {
        this.application = application;
        this.index = index;
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applicant_card, container, false);
        if (application == null) throw new RuntimeException("ApplicationCardFragment.setupCard needs to be called first before this fragment can be used");
        applicantNameText = view.findViewById(R.id.applicantName);
        applicantAccept = view.findViewById(R.id.applicantAccept);
        applicantDecline = view.findViewById(R.id.applicantDecline);

        applicantAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, ApplicantCardFragment.this, index, true);
            }
        });
        applicantDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, ApplicantCardFragment.this, index, false);
            }
        });

        applicantNameText.setText(application.getDonor().getName());
        return view;
    }

    public interface OnApplicantClicked {

        void onClick(View view, ApplicantCardFragment frag, int index, boolean accepted);
    }

}
