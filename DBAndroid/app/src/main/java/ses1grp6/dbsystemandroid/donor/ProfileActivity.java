package ses1grp6.dbsystemandroid.donor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Donor donor = (Donor) getIntent().getSerializableExtra("donor");
        TextView t = ((TextView)findViewById(R.id.idProfileTV));
        t.setText("" + donor.getId());
        ((TextView)findViewById(R.id.nameProfileTV)).setText(donor.getName());
        ((TextView)findViewById(R.id.emailProfileTV)).setText(donor.getEmail());
        ((TextView)findViewById(R.id.phoneProfileTV)).setText(donor.getPhone());
    }
}
