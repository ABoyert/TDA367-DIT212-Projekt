package tda367.paybike.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tda367.paybike.R;
import tda367.paybike.database.DatabaseController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Set TAG to class name for use in debugging
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button findBikeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBikeBtn = (Button) findViewById(R.id.findBikeBtn);

        // This line need to be here atm (I think)
        DatabaseController db = DatabaseController.getInstance();

        findBikeBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, BikeFeedActivity.class));
        });
    }

    @Override
    public void onClick(View v) {

    }
}