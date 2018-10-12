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
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomBikeAdAdapter;
import tda367.paybike.model.Rentable;
import tda367.paybike.viewmodels.MyRentablesViewModel;

public class MyRentablesActivity extends AppCompatActivity {

    private GridView rentablesGrid;
    private CustomBikeAdAdapter rentablesAdapter;
    private MyRentablesViewModel viewModel;
    private FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_rentables);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.sharp_menu_white_18dp);
        toolbar.setOverflowIcon(drawable);

        contentFrame = (FrameLayout) findViewById(R.id.contentFrame);

        viewModel = ViewModelProviders.of(this).get(MyRentablesViewModel.class);

        rentablesGrid = (GridView) findViewById(R.id.myRentablesGrid);
        rentablesAdapter = new CustomBikeAdAdapter(this,
                R.layout.view_layout_my_rentable, viewModel.getCurrentUserRentables());
        if (!viewModel.getCurrentUserRentables().isEmpty()) {
            rentablesGrid.setAdapter(rentablesAdapter);
        } else {
            LayoutInflater.from(this).inflate(R.layout.no_available_rentables, contentFrame, true);
        }
        registerForContextMenu(rentablesGrid);

        rentablesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object bike = parent.getItemAtPosition(position);
                if (bike instanceof Rentable) {
                    viewModel.setSelected((Rentable) bike);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    private void updateAdapter() {
        rentablesAdapter.updateBikeView(viewModel.getCurrentUserRentables());
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Rentable selected = rentablesAdapter.getItem(((AdapterView.AdapterContextMenuInfo)menuInfo).position);
        viewModel.setSelected(selected);

        if (v.getId()==R.id.myRentablesGrid) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.rentable_settings_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.change_availibility:
                Toast.makeText(this, viewModel.getSelected().getName() + " is available",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.delete:
                Toast.makeText(this, viewModel.getSelected().getName() + " was deleted",
                        Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
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
}
