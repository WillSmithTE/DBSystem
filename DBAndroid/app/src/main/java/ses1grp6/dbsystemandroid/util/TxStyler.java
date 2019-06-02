package ses1grp6.dbsystemandroid.util;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.R;

public class TxStyler {

    private static TxStyler instance;
    private final Context appContext;
    private Object with;
    private int color;

    private TxStyler(Context appContext) {
        this.appContext = appContext;
        this.color = appContext.getResources().getColor(R.color.colorPrimaryDark);
        this.with = new ForegroundColorSpan(this.color);
    }

    public TxStyler(Object with) {
        this.appContext = instance.appContext;
        this.with = with;
    }

    public TxStyler(int color) {
        this.appContext = instance.appContext;
        this.color = color;
        this.with = new ForegroundColorSpan(color);
    }

    /**
     * Needs to be called before using TxStyler instance.
     * @param appContext Application context.
     */
    public static void initialise(Context appContext) {

        if (instance != null) {
            throw new RuntimeException("Trying to initialise UserData more than once?");
        } else {
            instance = new TxStyler(appContext);
        }
    }

    public static TxStyler getInstance() {
        return instance;
    }

    public void prefix(TextView text, String prefix, String s) {
        String fullStr = prefix + " " + s;
        SpannableString span = new SpannableString(fullStr);
        span.setSpan(with, prefix.length(), fullStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(span);
    }
}
