package tda367.paybike.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.GridView;
import android.widget.SearchView;

import java.util.ArrayList;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomBikeAdAdapter;
import tda367.paybike.model.Bike;
import tda367.paybike.viewmodels.BikeFeedViewModel;

/*
* This class is only responsible for the visible aspects of the BikeFeed
*/

public class BikeFeedActivity extends AppCompatActivity {

    private static BikeFeedViewModel viewModel;
    private static GridView bikeView;
    private static SearchView searchBikes;
    private static CustomBikeAdAdapter bikeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_feed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        viewModel = new BikeFeedViewModel();
        bikeView = (GridView) findViewById(R.id.bikeGrid);
        searchBikes = (SearchView) findViewById(R.id.searchBike);

        bikeAdapter = new CustomBikeAdAdapter(this,
                R.layout.view_layout_bike_ad, viewModel.getAvailableBikes());
        bikeView.setAdapter(bikeAdapter);

        searchBikes.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    bikeView.clearTextFilter();
                } else {
                    bikeAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
    }
}
