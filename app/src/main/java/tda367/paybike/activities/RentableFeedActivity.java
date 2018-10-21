package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import java.util.ArrayList;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomRentableAdapter;
import tda367.paybike.fragments.RentableDetailsFragment;
import tda367.paybike.fragments.SendRequestFragment;
import tda367.paybike.model.Rentable;
import tda367.paybike.viewmodels.RentableViewModel;

/*
 * Created by Julia Gustafsson
 *
 * This Activity gives the user the ability to view all available bikes.
 * It implements listeners from two fragments which are used to view details about a bike
 * and send requests to bike owners about rental.
 *
 * General note: All activities work in close relation with their respective ViewModel which
 * holds the data to be shown, while the activity itself is responsible for displaying it in the correct fashion.
 * In this case the ViewModel is shared between this Activity and the two Fragments.
 */

public class RentableFeedActivity extends AppCompatActivity
        implements RentableDetailsFragment.OnFragmentInteractionListener,
        SendRequestFragment.OnFragmentInteractionListener {

    /* Constants */
    private static final int DETAILS_FRAGMENT = 1;
    private static final int REQUEST_FRAGMENT = 2;
    private static final String TAG = RentableFeedActivity.class.getSimpleName(); // Used for logging

    /* Widgets */
    private static GridView rentableView;
    private static SearchView searchRentables;
    private static SwipeRefreshLayout swipeRefresh;

    /* Resources */
    private static RentableViewModel viewModel;
    private static CustomRentableAdapter rentableAdapter;  // Adapter to allow display of bikes in a GridView with custom Views

    /* Automatically called when Activity is created */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentable_feed);

        /* Configure Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        /* Configure menu */
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.sharp_menu_white_18dp);
        toolbar.setOverflowIcon(drawable);

        /* Configure widgets */
        viewModel = ViewModelProviders.of(this).get(RentableViewModel.class);
        searchRentables = findViewById(R.id.searchRentable);
        searchRentables.setIconified(false);
        searchRentables.clearFocus();

        /* Configure GridView which will display available bikes with assistance from the Custom Adapter */
        rentableView = findViewById(R.id.rentableGrid);
        rentableAdapter = new CustomRentableAdapter(this,
                R.layout.view_layout_rentable_ad, viewModel.getAvailableRentables());
        rentableView.setAdapter(rentableAdapter);

        /* Handle click events on one of the bikes */
        rentableView.setOnItemClickListener((parent, view, position, id) -> {
            Object bike = parent.getItemAtPosition(position);
            if (bike instanceof Rentable) {
                viewModel.select((Rentable) bike);
                viewFragment(DETAILS_FRAGMENT);
            }
        });

        /* Handles search events */
        searchRentables.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /*
                Retrieves search results from the ViewModel and updates the adapter
                to view selection of rentables.
                */
                ArrayList<Rentable> results = viewModel.getSearchResult(newText);
                ((CustomRentableAdapter) rentableView.getAdapter()).updateRentableView(results);
                return false;
            }
        });

        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorSchemeColors(Color.parseColor("#30d9af"));
        /* Enables user to swipe down to refresh the list of bikes */
        swipeRefresh.setOnRefreshListener(() -> {
            updateAdapter();
            new Handler().postDelayed(() -> swipeRefresh.setRefreshing(false), 2000);
        });
    }

    /*
    Load all bikes from db when activity is resumed
    */
    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    /*
    Called when user clicks Request in RentableDetailsFragment
    Responds by viewing SendRequestFragment and closing the old fragment
    */
    @Override
    public void onMakeRequest() {
        onBackPressed();
        viewFragment(REQUEST_FRAGMENT);
    }

    /*
    Called when user clicks Send Request in SendRequestFragment
    Responds by creating a new request and close the fragment
    */
    @Override
    public void onSendRequest() {
        viewModel.createNewRequest();
        onBackPressed();
    }

    /* Automatically called to inflate the menu in the hamburger icon in toolbar */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /* Automatically called when one of the menu items are clicked */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle item selection */
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

    /*
    Called when user types in search field
    ViewModel takes care of updating the adapter, hence showing rentables that match the search input
    */
    private void updateAdapter() {
        rentableAdapter.updateRentableView(viewModel.getAvailableRentables());
    }

    /* Creates and displays the fragment which is provided as an argument */
    private void viewFragment(int fragmentId) {
        if (fragmentId == DETAILS_FRAGMENT || fragmentId == REQUEST_FRAGMENT) {
            Fragment fragment;
            String fragmentString;
            if (fragmentId == DETAILS_FRAGMENT) {
                fragment = RentableDetailsFragment.newInstance(viewModel.getSelected());
                fragmentString = "DETAILS_FRAGMENT";
                Log.d(TAG, "Showing RentableDetailsFragment.");

            } else {
                fragment = SendRequestFragment.newInstance(viewModel.getSelected());
                fragmentString = "REQUEST_FRAGMENT";
                Log.d(TAG, "Showing SendRequestFragment.");
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            /* Defines animation of the transaction */
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                    R.anim.enter_from_right, R.anim.exit_to_right);
            transaction.addToBackStack(null);
            /* Allow to fragment to cover the area below the toolbar */
            transaction.add(R.id.fragment_frame, fragment, fragmentString).commit();
        } else {
            Log.e(TAG, "Given fragment doesn't exist. No action taken.");
        }
    }

    /* -- Support methods for menu actions -- */

    /* Starts the AddBikeActivity */
    private void startAddBikeActivity() {
        startActivity(new Intent(getApplicationContext(), AddBikeActivity.class));
    }

    /* Starts the MyRentablesActivity */
    private void startMyRentablesActivity() {
        startActivity(new Intent(getApplicationContext(), MyRentablesActivity.class));
    }

    /* Starts the ViewRequestActivity */
    private void startViewRequestActivity() {
        startActivity(new Intent(getApplicationContext(), RequestFeedActivity.class));
    }

    /* Signs out the user and closes the RentableFeedActivity */
    private void signOut() {
        viewModel.signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}