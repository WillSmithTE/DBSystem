package ses1grp6.dbsystemandroid.util.simpleResult;

import android.app.Activity;
import android.content.Intent;

import ses1grp6.dbsystemandroid.launch.LoginActivity;

public class ResultData {

    private static final String INTENT_TITLE = "resultTitle";
    private static final String INTENT_BODY = "resultBody";
    private static final String INTENT_BACK_ACTIVITY = "resultBackActivity";
    private static final String INTENT_BACK_MESSAGE = "resultBackMessage";
    public final String title;
    public final String body;
    private Class<? extends Activity> backActivity;
    private String backMessage;


    public ResultData(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void setBackActivity(Class<? extends Activity> backActivity, String backMessage) {
        this.backActivity = backActivity;
        this.backMessage = backMessage;
    }

    public Class<? extends Activity> getBackActivity() {
        return backActivity;
    }

    public boolean hasBackActivity() {
        return backActivity != null;
    }

    public void putToIntent(Intent intent) {
        intent.putExtra(INTENT_TITLE, title);
        intent.putExtra(INTENT_BODY, body);
        if (backActivity != null) {
            intent.putExtra(INTENT_BACK_ACTIVITY, backActivity.getName());
            intent.putExtra(INTENT_BACK_MESSAGE, backMessage);
        }
    }

    public String getBackMessage() {
        return backMessage;
    }

    public static ResultData getFromIntent(Intent intent) {
        String title = intent.getStringExtra(INTENT_TITLE);
        String body = intent.getStringExtra(INTENT_BODY);
        ResultData resultData = new ResultData(title, body);

        if (intent.hasExtra(INTENT_BACK_ACTIVITY)) {

            try {
                Class<Activity> activity = (Class<Activity>) Class.forName(intent.getStringExtra(INTENT_BACK_ACTIVITY));
                resultData.setBackActivity(activity, intent.getStringExtra(INTENT_BACK_MESSAGE));
            } catch (ClassNotFoundException e) {
                System.err.println("Could not find activity to go back to: " + e.getMessage());
                resultData.setBackActivity(LoginActivity.class, "Go Back To Login");
            } catch (ClassCastException e) {
                System.err.println("Could not setup activity to go back to: " + e.getMessage());
                resultData.setBackActivity(LoginActivity.class, "Go Back To Login");
            }
        }
        return resultData;
    }
}
