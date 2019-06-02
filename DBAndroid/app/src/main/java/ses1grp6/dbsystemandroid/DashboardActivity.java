package ses1grp6.dbsystemandroid;

import android.content.Intent;
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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import ses1grp6.dbsystemandroid.charity.CharityHistoryFragment;
import ses1grp6.dbsystemandroid.charity.ListingCharityFragment;
import ses1grp6.dbsystemandroid.donor.DonorHistoryFragment;
import ses1grp6.dbsystemandroid.donor.ProfileFragment;
import ses1grp6.dbsystemandroid.donor.ListingFragment;
import ses1grp6.dbsystemandroid.launch.LoginActivity;
import ses1grp6.dbsystemandroid.util.UserData;
import ses1grp6.dbsystemandroid.util.UserType;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navView;
    private static final String DONOR_DASHBOARD_NAME = "Donor Dashboard";
    private static final String CHARITY_DASHBOARD_NAME = "Charity Dashboard";
    private final List<Integer> simBackMenus = new LinkedList<>();
    private final List<String> simBackTitles = new LinkedList<>();

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

        // Pre-Load User Profile.
        UserData.getInstance().fetchUser(this, null);

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

        swapContainerFor(R.id.charityNavDashboard, new ListingCharityFragment(), CHARITY_DASHBOARD_NAME);
    }

    private void createDonorDashboard() {
        // Loads the donor_nav_menu.xml and actual donor navigation object.
        navView.setNavigationItemSelectedListener(new DonorNavigationMenu());
        navView.inflateMenu(R.menu.donor_nav_menu);

        // Set the selected item in the navView to dashboard.
        navView.setCheckedItem(R.id.donorNavDashboard);

        swapContainerFor(R.id.donorNavDashboard, new ListingFragment(), DONOR_DASHBOARD_NAME);
    }

    private void swapContainerFor(int resId, Fragment fragment, String title) {
        simBackMenus.add(resId);
        simBackTitles.add(title);
        swapContainerFor(fragment, title);
    }

    private void swapContainerFor(Fragment fragment, String title) {
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        getSupportActionBar().setTitle(title);
    }

    private void logOut() {
        UserData.getInstance().clearStoredUserData();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        if (simBackMenus.size() > 1) {
            int size = simBackMenus.size();
            navView.setCheckedItem(simBackMenus.get(size - 2));
            getSupportActionBar().setTitle(simBackTitles.get(size - 2));
            simBackMenus.remove(size - 1);
            simBackTitles.remove(size - 1);
        }
        super.onBackPressed();
    }

    private class DonorNavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int id = menuItem.getItemId();

            switch (id) {

                case R.id.donorNavDashboard:
                    swapContainerFor(id, new ListingFragment(), DONOR_DASHBOARD_NAME);
                    break;
                case R.id.donorNavProfile:
                    swapContainerFor(id, new ProfileFragment(), "Donor Profile");
                    break;
                case R.id.donorNavHistory:
                    DonorHistoryFragment fragHistory = new DonorHistoryFragment();
                    HashSet<Integer> filterHistory = new HashSet<>();
                    filterHistory.add(1);
                    filterHistory.add(2);
                    fragHistory.setFilter(filterHistory);
                    swapContainerFor(id, fragHistory, "Donor History");
                    break;
                case R.id.donorNavPending:
                    DonorHistoryFragment fragPending = new DonorHistoryFragment();
                    HashSet<Integer> filterPending = new HashSet<>();
                    filterPending.add(0);
                    fragPending.setFilter(filterPending);
                    swapContainerFor(id, fragPending, "Pending Applications");
                    break;
                case R.id.donorLogOut:
                    logOut();
                    break;

            }
            drawer.closeDrawer(Gravity.START);
            return true;
        }
    }

    private class CharityNavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int id = menuItem.getItemId();

            switch (id) {

                case R.id.charityNavDashboard:
                    swapContainerFor(id, new ListingCharityFragment(), CHARITY_DASHBOARD_NAME);
                    break;
                case R.id.charityNavHistory:
                    swapContainerFor(id, new CharityHistoryFragment(), "Charity History");
                    break;

                case R.id.charityNavProfile:
                    swapContainerFor(id, new ProfileFragment(), "Charity Profile");
                    break;
                case R.id.charityLogOut:
                    logOut();
                    break;
            }
            drawer.closeDrawer(Gravity.START);
            return true;
        }
    }
}
