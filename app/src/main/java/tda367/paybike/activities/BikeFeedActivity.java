package tda367.paybike.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tda367.paybike.R;
import tda367.paybike.viewmodels.BikeFeedViewModel;

/*
* This class is only responsible for the visible aspects of the BikeFeed
*/

public class BikeFeedActivity extends AppCompatActivity {

    private static BikeFeedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        viewModel = new BikeFeedViewModel();
    }

}
