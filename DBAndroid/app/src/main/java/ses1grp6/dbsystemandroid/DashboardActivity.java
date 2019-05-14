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
import android.view.MenuItem;
import android.widget.Toast;

import ses1grp6.dbsystemandroid.charity.CharityProfileFragment;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.navDrawer);
        NavigationView navView = findViewById(R.id.navView);
        navView.inflateMenu(R.menu.charity_nav_menu);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawerToggle.syncState();

        // TODO remove test code
        Fragment fragment = new CharityProfileFragment();
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.add(R.id.fragment_container, new CharityProfileFragment());
        transaction.commit();

        navView.setNavigationItemSelectedListener(new DonorNavigationMenu());
        // TODO end of test code

        Intent intent = getIntent();
        String loginChoice = intent.getStringExtra(DBSystemUtil.LOGIN_CHOICE);

        if (loginChoice.equals(DBSystemUtil.LOGIN_CHARITY_CHOICE)) {
            getSupportActionBar().setTitle("Charity Dashboard");
            createCharityDashboard();
        } else {
            getSupportActionBar().setTitle("Donor Dashboard");
            createDonorDashboard();
        }
    }

    private void createCharityDashboard() {

    }

    private void createDonorDashboard() {
        Toast.makeText(DashboardActivity.this, testGETMethod(),
                Toast.LENGTH_LONG).show();
    }

    public String testGETMethod(){
        SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
        String token = preferences.getString("token","");
        return token;
    }

    private static class DonorNavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            return false;
        }
    }

    private static class CharityNavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            return false;
        }
    }
}
