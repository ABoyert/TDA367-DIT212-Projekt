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
//import tda367.paybike.adapters.CustomeRequestAdapter;

public class RequestFeedActivity extends AppCompatActivity {

    private GridView myRequestsGrid;
    private CustomeRequestAdapter rAdapter;

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

        rAdapter = new CustomeRequestAdapter(this,getAllRequest);
        myRequestsGrid.setAdapter(rAdapter);
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
                //signOut();
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
        startActivity(new Intent(getApplicationContext(), RequestFeedActivity.class));
    }

}
