package ses1grp6.dbsystemandroid.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ses1grp6.dbsystemandroid.R;

/**
 * Helper Class for putting fragments inside intents and getting them back out again.
 */
public class FragBundler {

    private static final String INTENT_FRAG_NAME = "dynamicFragment";
    private Intent intent;

    public FragBundler(Intent intent) {
        this.intent = intent;
    }
    public void putToIntent(Intent intent, Class<Fragment> fragClz) {
        intent.putExtra(INTENT_FRAG_NAME, fragClz.getName());
    }

    public Class<Fragment> getFragmentClass() {
        String clzName = intent.getStringExtra(INTENT_FRAG_NAME);

        try {
            return (Class<Fragment>) Class.forName(clzName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not get fragment out of intent: " + e.getMessage());
        }
    }

    public boolean hasFragment() {
        return intent.hasExtra(INTENT_FRAG_NAME);
    }

    public Fragment getFragment() {
        String clzName = intent.getStringExtra(INTENT_FRAG_NAME);

        try {
            Class<Fragment> fragClz = (Class<Fragment>) Class.forName(clzName);
            return fragClz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Could not get fragment out of intent: " + e.getMessage());
        }
    }

    public void replaceWithFragment(FragmentManager fragManager, int replaceId) {
        Fragment fragment = getFragment();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(replaceId, fragment);
        fragTransaction.commit();
    }
}
