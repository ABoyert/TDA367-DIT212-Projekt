package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomRentableAdapter;
import tda367.paybike.model.Rentable;
import tda367.paybike.viewmodels.MyRentablesViewModel;

/*
 * Created by Julia Gustafsson
 *
 * This Activity gives the user the ability to view his/her own bikes.
 * The user is also provided with the options to delete items or update their availability status.
 *
 * General note: All activities work in close relation with their respective ViewModel which
 * holds the data to be shown, while the activity itself is responsible for displaying it in the correct fashion.
 */

public class MyRentablesActivity extends AppCompatActivity {

    /* Constants */
    private static final String TAG = MyRentablesActivity.class.getSimpleName(); // Used for logging

    /* Widgets */
    private GridView rentablesGrid;

    /* Resources */
    private CustomRentableAdapter rentablesAdapter;
    private MyRentablesViewModel viewModel;

    /* Automatically called when Activity is created */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rentables);

        viewModel = ViewModelProviders.of(this).get(MyRentablesViewModel.class);

        /* Configure toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        /* Configure menu */
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.sharp_menu_white_18dp);
        toolbar.setOverflowIcon(drawable);

        /* Configure grid of rentables with assistance from the CustomRentableAdapter */
        rentablesGrid = findViewById(R.id.myRentablesGrid);
        rentablesAdapter = new CustomRentableAdapter(this,
                R.layout.view_layout_my_rentable, viewModel.getCurrentUserRentables());
        rentablesGrid.setAdapter(rentablesAdapter);
        /* Allow long clicks on grid items which will display the options change availability and delete. */
        registerForContextMenu(rentablesGrid);

        /* If current user doesn't have any items to display, show message layout instead of list */
        if (viewModel.getCurrentUserRentables().isEmpty()) {
            LayoutInflater.from(this).inflate(R.layout.no_available_rentables, findViewById(R.id.contentFrame), true);
        }
    }

    /* In case Activity is resumed, update adapter to check for changes in Model */
    @Override
    protected void onResume() {
        super.onResume();
        rentablesAdapter.updateRentableView(viewModel.getCurrentUserRentables());

    }

    /* Automatically called when the menu is configured to inflate the menu with correct menu options */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu, this adds items to the action bar if it is present. */
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /* Automatically called when a menu item i selected */
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

    /*
     * Automatically called when a list item is long-clicked. Displays the options of changing
     * availability or deleting the rentable.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Fetch item and notify ViewModel that item is clicked
        Rentable selected = rentablesAdapter.getItem(((AdapterView.AdapterContextMenuInfo)menuInfo).position);
        viewModel.setSelected(selected);

        if (v.getId()==R.id.myRentablesGrid) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.rentable_settings_menu, menu);
        }
    }

    /* Defines the actions for the context menu. */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.change_availibility:
                Toast.makeText(this, viewModel.getSelected().getName() + " is available",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.delete:
                /* A deleted item will no longer exist in the database or the model */
                viewModel.deleteSelectedRentable();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /* -- Support methods for menu actions -- /*

    /* Starts the AddBikeActivity */
    private void startAddBikeActivity() {
        startActivity(new Intent(getApplicationContext(), AddBikeActivity.class));
    }

    /* Starts the MyRentablesActivity */
    private void startMyRentablesActivity() {
        startActivity(new Intent(getApplicationContext(), MyRentablesActivity.class));
    }

    /* Starts the ViewRequestActivity */
    //TODO Implement method startViewRequestActivity()
    private void startViewRequestActivity() {

    }

    /* Signs out the user and closes the RentableFeedActivity */
    private void signOut() {
        viewModel.signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
