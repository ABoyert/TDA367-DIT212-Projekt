package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import tda367.paybike.R;
import tda367.paybike.adapters.PlaceAutocompleteAdapter;
import tda367.paybike.viewmodels.AddBikeViewModel;

public class AddBikeActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
            new LatLng(-34.041458, 150.790100), new LatLng(-33.682247, 151.383362));
    private static final String TAG = AddBikeActivity.class.getSimpleName();


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

        mGeoDataClient = Places.getGeoDataClient(this);

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGeoDataClient, BOUNDS_GREATER_SYDNEY, null);
        bikePosition = (AutoCompleteTextView) findViewById(R.id.positionText);
        bikePosition.setAdapter(mPlaceAutocompleteAdapter);
        bikePosition.setOnItemClickListener(mAutocompleteClickListener);


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

    /**
     * Listener that handles selections from suggestions from the AutoCompleteTextView that
     * displays Place suggestions.
     * Gets the place id of the selected item and issues a request to the Places Geo Data Client
     * to retrieve more details about the place.
     *
     * @see GeoDataClient#getPlaceById(String...)
     */
    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */
            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(position);
            final String placeId = item.getPlaceId();
            final CharSequence primaryText = item.getPrimaryText(null);

            Log.i(TAG, "Autocomplete item selected: " + primaryText);

            /*
             Issue a request to the Places Geo Data Client to retrieve a Place object with
             additional details about the place.
              */
            Task<PlaceBufferResponse> placeResult = mGeoDataClient.getPlaceById(placeId);
            placeResult.addOnCompleteListener(mUpdatePlaceDetailsCallback);

            Toast.makeText(getApplicationContext(), "Clicked: " + primaryText,
                    Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
        }
    };

    /**
     * Callback for results from a Places Geo Data Client query that shows the first place result in
     * the details view on screen.
     */
    private OnCompleteListener<PlaceBufferResponse> mUpdatePlaceDetailsCallback
            = new OnCompleteListener<PlaceBufferResponse>() {
        @Override
        public void onComplete(Task<PlaceBufferResponse> task) {
            try {
                PlaceBufferResponse places = task.getResult();

                // Get the Place object from the buffer.
                final Place place = places.get(0);

                // Format details of the place for display and show it in a TextView.
               /* mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
                        place.getId(), place.getAddress(), place.getPhoneNumber(),
                        place.getWebsiteUri()));*/

                // Display the third party attributions if set.
               /* final CharSequence thirdPartyAttribution = places.getAttributions();
                if (thirdPartyAttribution == null) {
                    mPlaceDetailsAttribution.setVisibility(View.GONE);
                } else {
                    mPlaceDetailsAttribution.setVisibility(View.VISIBLE);
                    mPlaceDetailsAttribution.setText(
                            Html.fromHtml(thirdPartyAttribution.toString()));
                }*/

                Log.i(TAG, "Place details received: " + place.getName());

                places.release();
            } catch (RuntimeRemoteException e) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete.", e);
                return;
            }
        }
    };

   /* private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e(TAG, res.getString("", name, id, address, phoneNumber,
                websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
    }*/

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
