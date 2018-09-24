package tda367.paybike.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tda367.paybike.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Set TAG to class name for use in debugging
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button bikeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bikeButton = (Button) findViewById(R.id.bikeButton);

        bikeButton.setOnClickListener(v -> {
            startActivity(new Intent(this, BikeFeedActivity.class));
        });
    }

    @Override
    public void onClick(View v) {

    }
}