package ses1grp6.dbsystemandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ses1grp6.dbsystemandroid.charity.ListingCharityFragment;
import ses1grp6.dbsystemandroid.charity.CharityProfileFragment;
import ses1grp6.dbsystemandroid.donor.DonorHistoryFragment;
import ses1grp6.dbsystemandroid.donor.DonorListFragment;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navView;
    private LoginChoice loginChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.navDrawer);
        navView = findViewById(R.id.navView);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawerToggle.syncState();

        // Get Login Choice From intent
        Intent intent = getIntent();
        loginChoice = LoginChoice.getFromIntent(intent);

        // TODO remove test code
//        Fragment fragment = new CharityProfileFragment();
//        FragmentManager fragManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragManager.beginTransaction();
//        transaction.add(R.id.fragment_container, fragment);
//        transaction.add(R.id.fragment_container, new CharityProfileFragment());
//        transaction.commit();
        // TODO end of test code

        //Intent intent = getIntent();
        //LoginChoice loginChoice = LoginChoice.getFromIntent(intent);
      
      if (loginChoice == LoginChoice.CHARITY) {
            getSupportActionBar().setTitle("Charity Dashboard");
            createCharityDashboard();
        } else {
            getSupportActionBar().setTitle("Donor Dashboard");
            createDonorDashboard();
        }
    }

    private void createCharityDashboard() {
        // Loads the charity_nav_menu.xml and actual charity navigation object.
        navView.setNavigationItemSelectedListener(new DonorNavigationMenu());
        navView.inflateMenu(R.menu.charity_nav_menu);

        // Set the selected item in the navView to dashboard.
        navView.setCheckedItem(R.id.charityNavDashboard);

        Fragment fragment = new ListingCharityFragment();
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void createDonorDashboard() {
        Toast.makeText(DashboardActivity.this, getToken(),
                Toast.LENGTH_LONG).show();

        // Loads the donor_nav_menu.xml and actual donor navigation object.
        navView.setNavigationItemSelectedListener(new DonorNavigationMenu());
        navView.inflateMenu(R.menu.donor_nav_menu);

        // Set the selected item in the navView to dashboard.
        navView.setCheckedItem(R.id.donorNavDashboard);

        Fragment fragment = new DonorListFragment();
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private String getToken(){
        SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
        String token = preferences.getString("token","");
        return token;
    }

    @Override
    public void onBackPressed() {
        if (loginChoice == LoginChoice.CHARITY) {
            getSupportActionBar().setTitle("Charity Dashboard");
            createCharityDashboard();
        } else {
            getSupportActionBar().setTitle("Donor Dashboard");
            createDonorDashboard();
        }
    }

    private void swapContainerFor(Fragment fragment) {
          FragmentManager fragManager = getSupportFragmentManager();
          FragmentTransaction transaction = fragManager.beginTransaction();
          transaction.replace(R.id.fragment_container, fragment);
          transaction.commit();
    }

    private class DonorNavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {

                case R.id.donorNavDashboard:
                    swapContainerFor(new DonorListFragment());
                    break;
                case R.id.donorNavHistory:
                    swapContainerFor(new DonorHistoryFragment());
                    break;

            }
            drawer.closeDrawer(Gravity.START);
            return true;
        }
    }

    private class CharityNavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            return false;
        }
    }
}
