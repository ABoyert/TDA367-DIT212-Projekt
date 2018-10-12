package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import java.util.ArrayList;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomBikeAdAdapter;
import tda367.paybike.fragments.RentableDetailsFragment;
import tda367.paybike.model.Rentable;
import tda367.paybike.viewmodels.BikeViewModel;

import static android.widget.AdapterView.*;

/**
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class is only responsible for the visible aspects of the BikeFeed
 *
 */

public class BikeFeedActivity extends AppCompatActivity
        implements RentableDetailsFragment.OnFragmentInteractionListener {

    @NonNull
    private static BikeViewModel viewModel;

    @NonNull
    private static GridView bikeView;

    @NonNull
    private static SearchView searchBikes;

    @NonNull
    private static CustomBikeAdAdapter bikeAdapter;

    @NonNull
    private static FrameLayout bikeDetailsContainer;

    @NonNull
    private ImageButton addBikeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bike_feed);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.sharp_menu_white_18dp);
        toolbar.setOverflowIcon(drawable);

        bikeDetailsContainer = (FrameLayout) findViewById(R.id.fragment_frame);

        // Load ViewModel
        viewModel = ViewModelProviders.of(this).get(BikeViewModel.class);

        // Locate the search view
        searchBikes = (SearchView) findViewById(R.id.searchBike);

        // Configure the grid of available bikes
        bikeView = (GridView) findViewById(R.id.bikeGrid);
        bikeAdapter = new CustomBikeAdAdapter(this,
                R.layout.view_layout_bike_ad, viewModel.getAvailableRentables());
        bikeView.setAdapter(bikeAdapter);

        // If one of the bikes is clicked
        bikeView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object bike = parent.getItemAtPosition(position);
                if (bike instanceof Rentable) {
                    viewModel.select((Rentable) bike);
                    viewBikeDetails((Rentable) bike);
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
                ArrayList<Rentable> results = viewModel.getSearchResult(newText);
                ((CustomBikeAdAdapter)bikeView.getAdapter()).updateBikeView(results);
                return false;
            }

        });
    }

    // Load all bikes from db when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.view_all_bikes:
                return true;
            case R.id.add_bike:
                startAddBikeActivity();
                return true;
            case R.id.view_my_bikes:
                startMyRentablesActivity();
                return true;
            case R.id.view_requests:
                startViewRequestActivity();
                return true;
            case R.id.sign_out:
                signOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startAddBikeActivity() {
        startActivity(new Intent(getApplicationContext(), AddBikeActivity.class));
    }

    private void startMyRentablesActivity() {
        startActivity(new Intent(getApplicationContext(), MyRentablesActivity.class));
    }

    //TODO Implement method
    private void startViewRequestActivity() {

    }

    //TODO Implement method
    private void signOut() {

    }

    private void updateAdapter() {
        bikeAdapter.updateBikeView(viewModel.getAvailableRentables());
    }

    private void viewBikeDetails(Rentable rentable) {
        RentableDetailsFragment bikeDetails = RentableDetailsFragment.newInstance(rentable);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_frame, bikeDetails, "BIKE_DETAILS_FRAGMENT").commit();
    }


}
