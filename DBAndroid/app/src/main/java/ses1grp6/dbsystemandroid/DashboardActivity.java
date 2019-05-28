package ses1grp6.dbsystemandroid;

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

import ses1grp6.dbsystemandroid.charity.CharityHistoryFragment;
import ses1grp6.dbsystemandroid.charity.CharityProfileFragment;
import ses1grp6.dbsystemandroid.charity.ListingCharityFragment;
import ses1grp6.dbsystemandroid.donor.DonorHistoryFragment;
import ses1grp6.dbsystemandroid.donor.DonorListFragment;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.util.UserType;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navView;
    private static final String DONOR_DASHBOARD_NAME = "Donor Dashboard";
    private static final String CHARITY_DASHBOARD_NAME = "Charity Dashboard";

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
      
      if (UserData.getInstance().getUserType() == UserType.CHARITY) {
            createCharityDashboard();
        } else {
            createDonorDashboard();
        }
    }

    private void createCharityDashboard() {
        // Loads the charity_nav_menu.xml and actual charity navigation object.
        navView.setNavigationItemSelectedListener(new CharityNavigationMenu());
        navView.inflateMenu(R.menu.charity_nav_menu);

        // Set the selected item in the navView to dashboard.
        navView.setCheckedItem(R.id.charityNavDashboard);

        swapContainerFor(new ListingCharityFragment(), CHARITY_DASHBOARD_NAME);
    }

    private void createDonorDashboard() {
        // Loads the donor_nav_menu.xml and actual donor navigation object.
        navView.setNavigationItemSelectedListener(new DonorNavigationMenu());
        navView.inflateMenu(R.menu.donor_nav_menu);

        // Set the selected item in the navView to dashboard.
        navView.setCheckedItem(R.id.donorNavDashboard);

        swapContainerFor(new DonorListFragment(), DONOR_DASHBOARD_NAME);
    }

    @Override
    public void onBackPressed() {
        if (UserData.getInstance().getUserType() == UserType.CHARITY) {
            createCharityDashboard();
        } else {
            createDonorDashboard();
        }
    }

    private void swapContainerFor(Fragment fragment, String title) {
          FragmentManager fragManager = getSupportFragmentManager();
          FragmentTransaction transaction = fragManager.beginTransaction();
          transaction.replace(R.id.fragment_container, fragment);
          transaction.commit();
          getSupportActionBar().setTitle(title);
    }

    private class DonorNavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {

                case R.id.donorNavDashboard:
                    swapContainerFor(new DonorListFragment(), DONOR_DASHBOARD_NAME);
                    break;
                case R.id.donorNavHistory:
                    swapContainerFor(new DonorHistoryFragment(), "Donor History");
                    break;

            }
            drawer.closeDrawer(Gravity.START);
            return true;
        }
    }

    private class CharityNavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {

                case R.id.charityNavDashboard:
                    swapContainerFor(new ListingCharityFragment(), CHARITY_DASHBOARD_NAME);
                    break;
                case R.id.charityNavHistory:
                    swapContainerFor(new CharityHistoryFragment(), "Charity History");

                    break;

                case R.id.charityNavProfile:
                    swapContainerFor(new CharityProfileFragment(), CHARITY_DASHBOARD_NAME);
                    break;
            }
            drawer.closeDrawer(Gravity.START);
            return true;
        }
    }
}
