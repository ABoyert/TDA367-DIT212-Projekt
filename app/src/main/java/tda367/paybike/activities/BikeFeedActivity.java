package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import java.util.ArrayList;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomBikeAdAdapter;
import tda367.paybike.fragments.RentableDetailsFragment;
import tda367.paybike.fragments.SendRequestFragment;
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
        implements RentableDetailsFragment.OnFragmentInteractionListener,
                   SendRequestFragment.OnFragmentInteractionListener {


    private static final int DETAILS_FRAGMENT = 1;
    private static final int REQUEST_FRAGMENT = 2;
    private static final String TAG = BikeFeedActivity.class.getSimpleName();

    private static GridView bikeView;
    private static SearchView searchBikes;
    private static FrameLayout bikeDetailsContainer;

    private static BikeViewModel viewModel;
    private static CustomBikeAdAdapter bikeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_feed);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        // Setup menu
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.sharp_menu_white_18dp);
        toolbar.setOverflowIcon(drawable);

        // Locate visual components
        bikeDetailsContainer = findViewById(R.id.fragment_frame);
        viewModel = ViewModelProviders.of(this).get(BikeViewModel.class);
        searchBikes = findViewById(R.id.searchBike);
        searchBikes.setIconified(false);

        // Configure the grid of available bikes
        bikeView = findViewById(R.id.bikeGrid);
        bikeAdapter = new CustomBikeAdAdapter(this,
                R.layout.view_layout_bike_ad, viewModel.getAvailableRentables());
        bikeView.setAdapter(bikeAdapter);

        // If one of the bikes is clicked
        bikeView.setOnItemClickListener((parent, view, position, id) -> {
            Object bike = parent.getItemAtPosition(position);
            if (bike instanceof Rentable) {
                viewModel.select((Rentable) bike);
                viewFragment((Rentable) bike, DETAILS_FRAGMENT);
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

    // TODO, check if this also updates the model, it should
    // Load all bikes from db when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    // Called when user clicks Request in RentableDetailsFragment
    // Responds by viewing SendRequestFragment
    @Override
    public void onMakeRequest(Rentable rentable) {
        getFragmentManager().popBackStack();
        viewFragment(rentable, REQUEST_FRAGMENT);
    }

    @Override
    public void onSendRequest() {
        viewModel.createNewRequest();
        getFragmentManager().popBackStack();
    }

    private void updateAdapter() {
        bikeAdapter.updateBikeView(viewModel.getAvailableRentables());
    }

    private void viewFragment(Rentable rentable, int f) {
        if (f == DETAILS_FRAGMENT || f == REQUEST_FRAGMENT) {
            Fragment fragment;
            String fragmentString;
            if (f == DETAILS_FRAGMENT) {
                fragment = RentableDetailsFragment.newInstance(rentable);
                fragmentString = "DETAILS_FRAGMENT";
                Log.d(TAG, "Showing BikeDetailsFragment.");

            } else {
                fragment = SendRequestFragment.newInstance(rentable);
                fragmentString = "REQUEST_FRAGMENT";
                Log.d(TAG, "Showing SendRequestFragment.");
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                    R.anim.enter_from_right, R.anim.exit_to_right);
            transaction.addToBackStack(null);
            transaction.add(R.id.fragment_frame, fragment, fragmentString).commit();
        }
        else {
            Log.e(TAG, "Given fragment doesn't exist. No action taken.");
        }
    }

    // Methods that handles the toolbar menu
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

    //TODO Implement method startViewRequestActivity()
    private void startViewRequestActivity() {

    }

    private void signOut() {
        viewModel.getRepository().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
