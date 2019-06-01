package ses1grp6.dbsystemandroid.donor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Charity;
import ses1grp6.dbsystemandroid.model.Donor;
import ses1grp6.dbsystemandroid.model.User;
import ses1grp6.dbsystemandroid.util.TxStyler;
import ses1grp6.dbsystemandroid.util.UserData;

public class ProfileFragment extends Fragment {

    TxStyler styler = TxStyler.getInstance();
    private View view;
    private ViewGroup viewGroup;
    private TextView nameText, emailText, contactText, createdAtText, descripText;
    private FrameLayout descipBox;
    private User user;

    public ProfileFragment() {
    }

    public void setProfileModel(User user) {
        this.user = user;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        viewGroup = (ViewGroup) view;

        nameText = view.findViewById(R.id.nameProfileTV);
        emailText = view.findViewById(R.id.emailProfileTV);
        contactText = view.findViewById(R.id.phoneProfileTV);
        createdAtText = view.findViewById(R.id.createdAtProfileTV);
        descripText = view.findViewById(R.id.descripProfileTV);
        descipBox = view.findViewById(R.id.descripProfileBox);
        // Donor does not need a description box so remove it.

        if (user == null) {
            buildUserProfile();
        } else {
            setupTextView(user);
        }
        return view;
    }

    public void buildUserProfile() {
        UserData.getInstance().fetchUser(this.getActivity(), new UserData.OnUserRecievedListener() {
            @Override
            public void onUserReceived(User user) {
                setupTextView(user);
            }
        });
    }

    private void setupTextView(User user) {
        nameText.setText(user.getName());
        styler.prefix(emailText, "Your Email: ", user.getEmail());

        if (user.hasContactNumber())
            styler.prefix(contactText, "Your Contact Number: ", user.getContactNumber());
        else
            viewGroup.removeView(contactText);

        if (user.hasCreatedAt())
            styler.prefix(createdAtText, "Your account was created at: ", user.getFormattedCreatedAt());
        else
            viewGroup.removeView(createdAtText);

        // Remove the description box if the user is a donor or if the charity does not have a description.
        if (user instanceof Donor || !((Charity)user).hasCharityDescription()) {
            viewGroup.removeView(descipBox);
        } else {
            descripText.setText(((Charity) user).getCharityDescription());
        }
    }


}
