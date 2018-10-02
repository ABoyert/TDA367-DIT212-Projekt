package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tda367.paybike.R;
import tda367.paybike.model.Bike;
import tda367.paybike.viewmodels.AddBikeViewModel;

public class AddBikeActivity extends AppCompatActivity {

    private Button addBikeBtn;
    private EditText bikeName, bikeDescription, bikePosition, bikePrice;
    private TextView warning;

    private AddBikeViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);

        model = ViewModelProviders.of(this).get(AddBikeViewModel.class);

        addBikeBtn = (Button) findViewById(R.id.postBikeBtn);
        addBikeBtn.setFocusable(true);

        bikeName = (EditText) findViewById(R.id.nameText);
        bikeName.setText(model.getBikeName());
        bikeName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    model.setBikeName(bikeName.getText().toString());
                }
            }
        });

        bikeDescription = (EditText) findViewById(R.id.descriptionText);
        bikeDescription.setText(model.getBikeDescription());
        bikeDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    model.setBikeDescription(bikeDescription.getText().toString());
                }
            }
        });

        // TODO Check if this needs to be updated when we have a position object to store addresses
        bikePosition = (EditText) findViewById(R.id.positionText);
        bikePosition.setText(model.getBikeDescription());
        bikePosition.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    model.setBikePosition(bikePosition.getText().toString());
                }
            }
        });

        bikePrice = (EditText) findViewById(R.id.priceText);
        bikePrice.setText(model.getBikePrice());
        bikePrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    model.setBikePrice(bikePrice.getText().toString());
                }
            }
        });

        warning = (TextView) findViewById(R.id.inputInvalidText);

        addBikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateModel();
                if (model.inputIsValid()) {
                    model.postBike();
                    clearAllTextFields();
                    if (model.warningIsShown()) {
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
        model.setBikeName(bikeName.getText().toString());
        model.setBikeDescription(bikeDescription.getText().toString());
        model.setBikePosition(bikePosition.getText().toString());
        model.setBikePrice(bikePrice.getText().toString());
    }

    private void clearAllTextFields() {
        bikeName.getText().clear();
        model.setBikeName("");
        bikeDescription.getText().clear();
        model.setBikeDescription("");
        bikePosition.getText().clear();
        model.setBikePosition("");
        bikePrice.getText().clear();
        model.setBikePrice("");
    }

    // Fetches warning message from ViewModel and shows it in the GUI
    private void showWarning() {
        warning.setText(model.getWarning());
        warning.setVisibility(View.VISIBLE);
        // Notify ViewModel of change
        model.showWarning(true);
    }

    // Hides warning message in the GUI
    private void hideWarning() {
        warning.setVisibility(View.GONE);
        warning.setText("");
        // Notify ViewModel of change
        model.showWarning(false);
    }
}
