package ses1group6.dbandroid.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.PreferenceFragmentCompat;

import ses1group6.dbandroid.NavigationActivity;
import ses1group6.dbandroid.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        ((NavigationActivity)getActivity()).hideLoadingIcon();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(getContext() != null){
            PreferenceManager.getDefaultSharedPreferences(getContext()).registerOnSharedPreferenceChangeListener((NavigationActivity)getActivity());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getContext() != null){
            PreferenceManager.getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener((NavigationActivity)getActivity());
        }
    }
}
