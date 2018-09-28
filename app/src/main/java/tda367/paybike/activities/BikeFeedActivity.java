package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import java.util.ArrayList;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomBikeAdAdapter;
import tda367.paybike.fragments.BikeDetailsFragment;
import tda367.paybike.model.Bike;
import tda367.paybike.viewmodels.BikeViewModel;

import static android.widget.AdapterView.*;

/**
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class is only responsible for the visible aspects of the BikeFeed
 *
 */

public class BikeFeedActivity extends AppCompatActivity
        implements BikeDetailsFragment.OnFragmentInteractionListener {

    @NonNull
    private static BikeViewModel viewModel;

    @NonNull
    private static GridView bikeView;

    @NonNull
    private static SearchView searchBikes;

    @NonNull
    private static CustomBikeAdAdapter bikeAdapter;

    private static FrameLayout bikeDetailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_feed);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        bikeDetailsContainer = (FrameLayout) findViewById(R.id.fragment_frame);

        // Load ViewModel
        viewModel = ViewModelProviders.of(this).get(BikeViewModel.class);

        // Locate the search view
        searchBikes = (SearchView) findViewById(R.id.searchBike);

        // Configure the grid of available bikes
        bikeView = (GridView) findViewById(R.id.bikeGrid);
        bikeAdapter = new CustomBikeAdAdapter(this,
                R.layout.view_layout_bike_ad, viewModel.getAvailableBikes());
        bikeView.setAdapter(bikeAdapter);

        // If one of the bikes is clicked
        bikeView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object bike = parent.getItemAtPosition(position);
                if (bike instanceof Bike) {
                    viewModel.select((Bike) bike);
                    viewBikeDetails((Bike) bike);
                }
            }

        });

        // Handle search events
        searchBikes.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Bike> results = viewModel.getSearchResult(newText);
                ((CustomBikeAdAdapter)bikeView.getAdapter()).updateBikeView(results);
                return false;
            }

        });
    }

    private void viewBikeDetails(Bike bike) {
        BikeDetailsFragment bikeDetails = BikeDetailsFragment.newInstance(bike.getId(), bike.getOwner(), bike.getPosition(), bike.getPrice());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_frame, bikeDetails, "BIKE_DETAILS_FRAGMENT").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
