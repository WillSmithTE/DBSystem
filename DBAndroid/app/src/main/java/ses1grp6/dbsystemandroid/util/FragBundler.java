package ses1grp6.dbsystemandroid.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ses1grp6.dbsystemandroid.R;

/**
 * Helper Class for putting fragments inside intents and getting them back out again.
 */
public class FragBundler {

    private static final String INTENT_FRAG_NAME = "dynamicFragment";
    private static final String INTENT_FRAG_MODEL = "dynamicFragmentModel";
    private Intent intent;

    public FragBundler(Intent intent) {
        this.intent = intent;
    }

    public void putToIntent(Class<? extends Fragment> fragClz) {
        intent.putExtra(INTENT_FRAG_NAME, fragClz.getName());
    }

    public <M extends Parcelable> void putToIntent(Class<? extends Fragment> fragClz, M model) {
        putToIntent(fragClz);
        intent.putExtra(INTENT_FRAG_MODEL, model);
    }

    public Class<? extends Fragment> getFragmentClass() {
        String clzName = intent.getStringExtra(INTENT_FRAG_NAME);

        try {
            return (Class<? extends Fragment>) Class.forName(clzName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not get fragment out of intent: " + e.getMessage());
        }
    }

    public boolean hasFragment() {
        return intent.hasExtra(INTENT_FRAG_NAME);
    }

    public boolean hasModel() {
        return intent.hasExtra(INTENT_FRAG_MODEL);
    }

    @SuppressWarnings("unchecked")
    public <M extends Parcelable> M getModel() {
        if (!hasModel()) throw new RuntimeException("Could not find model in intent");
        return (M) intent.getParcelableExtra(INTENT_FRAG_MODEL);
    }

    public Fragment getFragment() {
        if (!hasFragment()) throw new RuntimeException("Could not find Dynamic Fragment in intent");
        String clzName = intent.getStringExtra(INTENT_FRAG_NAME);

        try {
            Class<? extends Fragment> fragClz = (Class<Fragment>) Class.forName(clzName);
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
