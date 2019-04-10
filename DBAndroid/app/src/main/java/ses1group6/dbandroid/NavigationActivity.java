package ses1group6.dbandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import ses1group6.dbandroid.fragments.HomeFragment;
import ses1group6.dbandroid.fragments.SettingsFragment;
import ses1group6.dbandroid.fragments.SetupFragment;

public class NavigationActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        SharedPreferences.OnSharedPreferenceChangeListener
        {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private ProgressBar loadingIcon;
    private SharedPreferences sharedPreferences;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = findViewById(R.id.navigation_toolbar);
        navigationView = findViewById(R.id.navigation_view);
        loadingIcon = findViewById(R.id.loading_icon);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        setSupportActionBar(toolbar);
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(this);
        updateView(HomeFragment.class);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userName = sharedPreferences.getString(getString(R.string.pref_key_personal_name), "No Profile Name!");

        testFirstLogin();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        TextView userNameTV = navigationView.getHeaderView(0).findViewById(R.id.profile_name);
        userNameTV.setText(userName);
    }

    private void testFirstLogin() {
        if (sharedPreferences.getBoolean("firststart", true)) {
            sharedPreferences.edit().putBoolean("firststart", false).apply();
            updateView(SetupFragment.class);
        }
    }

    public void updateView(Class fragmentClass) {
        Fragment fragment = null;
        showLoadingIcon();

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            hideLoadingIcon();
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_frame, fragment).commit();

        drawerLayout.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        drawerToggle.onConfigurationChanged(configuration);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        updateView(HomeFragment.class);

        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Class fragmentClass;

        switch (item.getItemId()) {
            case R.id.home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.logout:
                startActivity(new Intent(NavigationActivity.this, LoginActivity.class));
                finish();
                return true;
            default:
                return false;
        }

        setTitle(item.getTitle());
        item.setChecked(true);

        updateView(fragmentClass);
        return true;
    }

    public void hideLoadingIcon(){
        loadingIcon.setVisibility(View.GONE);
    }

    public void showLoadingIcon(){
        loadingIcon.setVisibility(View.VISIBLE);
    }


    public SharedPreferences getSharedPreferences(){
        return this.sharedPreferences;
    }
    public NavigationView getNavigationView(){
        return this.navigationView;
    }


            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            }
        }
