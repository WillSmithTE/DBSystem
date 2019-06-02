package ses1grp6.dbsystemandroid.charity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.donor.ApplicantCardFragment;
import ses1grp6.dbsystemandroid.model.Application;
import ses1grp6.dbsystemandroid.network.DBSystemNetwork;
import ses1grp6.dbsystemandroid.network.RequestResponse;

public class ApplicantAcceptFragment extends Fragment {

    private View view;
    private Application application;
    private Button acceptButton;
    private Button rejectButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_application_options, container, false);
        acceptButton = view.findViewById(R.id.appliAcceptButton);
        rejectButton = view.findViewById(R.id.appliRejectButton);

        Intent intent = getActivity().getIntent();
        application = Application.getFromIntent(intent);

        modifyLayout();
        setListeners();
        return view;
    }

    private void setListeners() {
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Accept Applicant")
                        .setMessage(getString(R.string.applicant_accept_confirmation))
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                acceptApplicant();
                            }
                        })
                        .show();
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Reject Applicant")
                        .setMessage(getString(R.string.applicant_reject_confirmation))
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                rejectApplicant();
                            }
                        })
                        .show();
            }
        });
    }

    private void rejectApplicant() {
        DBSystemNetwork.sendGetRequest(getActivity(), "application/reject/" + application.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (!response.hasStatusSuccessful()) {
                    Toast.makeText(getContext(), "Unable tell server to reject application", Toast.LENGTH_LONG).show();
                    System.err.println(response.getErrorMessage());
                } else {
                    Toast.makeText(getContext(), "Applicant Rejected", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }
        });
    }

    private void acceptApplicant() {
        DBSystemNetwork.sendGetRequest(getActivity(), "application/accept/" + application.getId(), new DBSystemNetwork.OnRequestComplete() {
            @Override
            public void onRequestCompleted(RequestResponse response) {

                if (!response.hasStatusSuccessful()) {
                    Toast.makeText(getContext(), "Unable tell server to accept application", Toast.LENGTH_LONG).show();
                    System.err.println(response.getErrorMessage());
                } else {
                    Toast.makeText(getContext(), "Applicant Successfully Accepted!", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }
        });
    }

    private void modifyLayout() {
        FragmentActivity activity = getActivity();
        LinearLayout mainLayout = activity.findViewById(R.id.appliMainLayout);
        LinearLayout listLayout = activity.findViewById(R.id.appliListLayout);
        TextView title = activity.findViewById(R.id.appliTitle);

        Intent intent = activity.getIntent();
        Application application = Application.getFromIntent(intent);

        mainLayout.removeView(listLayout);
        title.setText(application.getDonor().getName() + "'s Application");
    }
}
