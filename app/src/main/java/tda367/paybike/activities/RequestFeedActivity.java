package tda367.paybike.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.GridView;

import java.security.PublicKey;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomeRequestAdapter;
import tda367.paybike.handlers.RequestHandler;
//import tda367.paybike.adapters.CustomeRequestAdapter;

public class RequestFeedActivity extends AppCompatActivity {

    private GridView myRequestsGrid;
    private CustomeRequestAdapter rAdapter;
    private RequestHandler requestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_feed);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.sharp_menu_white_18dp);
        toolbar.setOverflowIcon(drawable);

        myRequestsGrid = (GridView) findViewById(R.id.myRequestsGrid);

        // Configure the grid of available bikes
        //requestAdapter = new CustomeRequestAdapter()

        /*
        rAdapter = new CustomeRequestAdapter(this,
                R.layout.view_layout_my_request, requestHandler.getCurrentRequests());

        myRequestsGrid.setAdapter(rAdapter);*/
    }


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
        rAdapter.updateBikeView(requestHandler.getCurrentRequests());
    }

    private  void startBikeFeedActivity(){
        startActivity(new Intent(getApplicationContext(), BikeFeedActivity.class));
    }

    private void startAddBikeActivity() {
        startActivity(new Intent(getApplicationContext(), AddBikeActivity.class));
    }

    private void startMyRentablesActivity() {
        startActivity(new Intent(getApplicationContext(), MyRentablesActivity.class));
    }

    private void startViewRequestActivity() {
        startActivity(new Intent(getApplicationContext(), RequestFeedActivity.class));
    }

    //TODO Implement method
    private void signOut() {

    }
}
