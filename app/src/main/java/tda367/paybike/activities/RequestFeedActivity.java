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
import java.util.List;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomeRequestAdapter;
import tda367.paybike.handlers.RequestHandler;
import tda367.paybike.model.Request;

public class RequestFeedActivity extends AppCompatActivity {

    private GridView myRequestsGrid;
    private CustomeRequestAdapter rAdapter;
    private RequestHandler requestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_feed);

        requestHandler = RequestHandler.getInstance();

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.sharp_menu_white_18dp);
        toolbar.setOverflowIcon(drawable);

        // Configure the grid of available bikes
        myRequestsGrid = (GridView) findViewById(R.id.myRequestsGrid);
        rAdapter = new CustomeRequestAdapter(this,
                R.layout.view_layout_my_request, requestHandler.getCurrentRequests());
        myRequestsGrid.setAdapter(rAdapter);
    }

    public List<Request> getPersonalRequests(List<Request> requests) {
        //TODO Implement method the get personal requests

        return requests;
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

    /*
    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }*/

    private void updateAdapter() {
        rAdapter.updateBikeView(requestHandler.getCurrentRequests());
    }



    //Starts the RentableFeedActivity
    private  void startBikeFeedActivity(){
        startActivity(new Intent(getApplicationContext(), RentableFeedActivity.class));
    }

    //Starts the AddBikeActivity
    private void startAddBikeActivity() {
        startActivity(new Intent(getApplicationContext(), AddBikeActivity.class));
    }

    //Starts the MyRentablesActivity
    private void startMyRentablesActivity() {
        startActivity(new Intent(getApplicationContext(), MyRentablesActivity.class));
    }

    //Starts the ViewRequestsActivity
    private void startViewRequestActivity() {
        startActivity(new Intent(getApplicationContext(), RequestFeedActivity.class));
    }

    //Method that Sign out out the user
    private void signOut() {
        //TODO Implement method
    }
}
