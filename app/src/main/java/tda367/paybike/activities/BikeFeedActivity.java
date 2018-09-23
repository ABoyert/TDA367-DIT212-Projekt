package tda367.paybike.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomBikeAdAdapter;
import tda367.paybike.viewmodels.BikeFeedViewModel;

/*
* This class is only responsible for the visible aspects of the BikeFeed
*/

public class BikeFeedActivity extends AppCompatActivity {

    private static BikeFeedViewModel viewModel;
    private ListView bikeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = new BikeFeedViewModel();

        CustomBikeAdAdapter bikeAdapter = new CustomBikeAdAdapter(this,
                R.layout.view_layout_bike_ad, viewModel.getAvailableBikes());

        bikeView = (ListView) findViewById(R.id.bikeList);
        bikeView.setAdapter(bikeAdapter);
        }

}
