package tda367.paybike.activities;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomRequestAdapter;
import tda367.paybike.viewmodels.RequestFeedViewModel;

public class RequestFeedActivity extends AppCompatActivity {

    /* Widgets */
    private GridView myRequestsGrid;
    private SwipeRefreshLayout swipeRefresh;

    /* Resources */
    private CustomRequestAdapter rAdapter;
    private RequestFeedViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_feed);

        viewModel = ViewModelProviders.of(this).get(RequestFeedViewModel.class);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.sharp_menu_white_18dp);
        toolbar.setOverflowIcon(drawable);

        // Configure the grid of available bikes
        myRequestsGrid = findViewById(R.id.myRequestsGrid);
        rAdapter = new CustomRequestAdapter(this,
                R.layout.view_layout_my_request, viewModel.getRequests());
        myRequestsGrid.setAdapter(rAdapter);
        myRequestsGrid.setOnItemClickListener((parent, view, position, id) -> updateAdapter());

        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorSchemeColors(Color.parseColor("#30d9af"));
        /* Enables user to swipe down to refresh the list of bikes */
        swipeRefresh.setOnRefreshListener(() -> {
            updateAdapter();
            new Handler().postDelayed(() -> swipeRefresh.setRefreshing(false), 2000);
        });
    }

    /* Automatically called to inflate the menu in the hamburger icon in toolbar */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //'onOptionsItemSelected' method is automatically called when one of the menu items are clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.view_all_bikes:
                startBikeFeedActivity();
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

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    private void updateAdapter() {
        viewModel.updateRequests();
        rAdapter.updateBikeView(viewModel.getRequests());
    }

    /* Starts the RentableFeedActivity */
    private void startBikeFeedActivity() {
        startActivity(new Intent(getApplicationContext(), RentableFeedActivity.class));
    }

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