package ses1grp6.dbsystemandroid.util.simpleResult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ses1grp6.dbsystemandroid.DashboardActivity;
import ses1grp6.dbsystemandroid.R;

public class SimpleResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_result);
        Intent intent = getIntent();
        ResultData resultData = ResultData.getFromIntent(intent);

        TextView title = findViewById(R.id.resultTitle);
        TextView body = findViewById(R.id.resultBody);

        title.setText(resultData.title);
        body.setText(resultData.body);
    }

    public void onBackToDashboardClicked(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
