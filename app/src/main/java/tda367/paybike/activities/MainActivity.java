package tda367.paybike.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tda367.paybike.R;

public class MainActivity extends AppCompatActivity {
    // Set TAG to class name for use in debugging
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}