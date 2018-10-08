package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import tda367.paybike.R;
import tda367.paybike.adapters.PlaceAutocompleteAdapter;
import tda367.paybike.viewmodels.AddBikeViewModel;

public class AddBikeActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40,168), new LatLng(71, 136));

    private Button addBikeBtn;
    private EditText bikeName, bikeDescription, bikePrice;
    private AutoCompleteTextView bikePosition;
    private TextView warning;

    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private AddBikeViewModel viewModel;
    protected GeoDataClient mGeoDataClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);

        viewModel = ViewModelProviders.of(this).get(AddBikeViewModel.class);

        addBikeBtn = (Button) findViewById(R.id.postBikeBtn);
        addBikeBtn.setFocusable(true);

        bikeName = (EditText) findViewById(R.id.nameText);
        bikeName.setText(viewModel.getBikeName());
        bikeName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    viewModel.setBikeName(bikeName.getText().toString());
                }
            }
        });

        bikeDescription = (EditText) findViewById(R.id.descriptionText);
        bikeDescription.setText(viewModel.getBikeDescription());
        bikeDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    viewModel.setBikeDescription(bikeDescription.getText().toString());
                }
            }
        });

        mGeoDataClient = Places.getGeoDataClient(this, null);

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGeoDataClient, LAT_LNG_BOUNDS, null);
        bikePosition = (AutoCompleteTextView) findViewById(R.id.positionText);
        bikePosition.setAdapter(mPlaceAutocompleteAdapter);


        bikePrice = (EditText) findViewById(R.id.priceText);
        bikePrice.setText(viewModel.getBikePrice());
        bikePrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    viewModel.setBikePrice(bikePrice.getText().toString());
                }
            }
        });

        warning = (TextView) findViewById(R.id.inputInvalidText);

        addBikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateModel();
                if (viewModel.inputIsValid()) {
                    viewModel.postBike();
                    clearAllTextFields();
                    if (viewModel.warningIsShown()) {
                        hideWarning();
                    }
                    finish(); // End Activity
                } else {
                    showWarning();
                }
            }
        });
    }

    private void updateModel() {
        viewModel.setBikeName(bikeName.getText().toString());
        viewModel.setBikeDescription(bikeDescription.getText().toString());
        //TODO fix this
        //viewModel.setBikePosition(bikePosition.getText().toString());
        viewModel.setBikePrice(bikePrice.getText().toString());
    }

    private void clearAllTextFields() {
        bikeName.getText().clear();
        viewModel.setBikeName("");
        bikeDescription.getText().clear();
        viewModel.setBikeDescription("");
        bikePosition.getText().clear();
        //TODO fix this
        //viewModel.setBikePosition("");
        bikePrice.getText().clear();
        viewModel.setBikePrice("");
    }

    // Fetches warning message from ViewModel and shows it in the GUI
    private void showWarning() {
        warning.setText(viewModel.getWarning());
        warning.setVisibility(View.VISIBLE);
        // Notify ViewModel of change
        viewModel.showWarning(true);
    }

    // Hides warning message in the GUI
    private void hideWarning() {
        warning.setVisibility(View.GONE);
        warning.setText("");
        // Notify ViewModel of change
        viewModel.showWarning(false);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
