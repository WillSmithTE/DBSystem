package ses1grp6.dbsystemandroid.util.simpleResult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.DashboardActivity;
import ses1grp6.dbsystemandroid.R;

public class SimpleResultActivity extends AppCompatActivity {

    ResultData resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_result);
        Intent intent = getIntent();
        resultData = ResultData.getFromIntent(intent);

        TextView title = findViewById(R.id.resultTitle);
        TextView body = findViewById(R.id.resultBody);
        Button button = findViewById(R.id.resultBackButton);

        title.setText(resultData.title);
        body.setText(resultData.body);

        if (!resultData.hasBackActivity()) {
            button.setVisibility(View.INVISIBLE);
        } else {
            button.setText(resultData.getBackMessage());
        }
    }

    public void onBack(View view) {

        if (resultData.hasBackActivity()) {
            Intent intent = new Intent(this, resultData.getBackActivity());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
