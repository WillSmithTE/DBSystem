package ses1grp6.dbsystemandroid.util.simpleResult;

import android.content.Intent;

public class ResultData {

    public final String title;
    public final String body;

    private static final String INTENT_TITLE = "resultTitle";
    private static final String INTENT_BODY = "resultBody";

    public ResultData(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void putToIntent(Intent intent) {
        intent.putExtra(INTENT_TITLE, title);
        intent.putExtra(INTENT_BODY, body);
    }

    public static ResultData getFromIntent(Intent intent) {
        String title = intent.getStringExtra(INTENT_TITLE);
        String body = intent.getStringExtra(INTENT_BODY);
        return new ResultData(title, body);
    }
}
