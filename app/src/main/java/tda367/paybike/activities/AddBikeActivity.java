package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tda367.paybike.R;
import tda367.paybike.viewmodels.AddBikeViewModel;

public class AddBikeActivity extends AppCompatActivity {

    private Button addBikeBtn;
    private EditText bikeName, bikeDescription, bikePosition, bikePrice;
    private TextView warning;

    private AddBikeViewModel viewModel;

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

        // TODO Check if this needs to be updated when we have a position object to store addresses
        bikePosition = (EditText) findViewById(R.id.positionText);
        bikePosition.setText(viewModel.getBikeDescription());
        bikePosition.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    viewModel.setBikePosition(bikePosition.getText().toString());
                }
            }
        });

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
        viewModel.setBikePosition(bikePosition.getText().toString());
        viewModel.setBikePrice(bikePrice.getText().toString());
    }

    private void clearAllTextFields() {
        bikeName.getText().clear();
        viewModel.setBikeName("");
        bikeDescription.getText().clear();
        viewModel.setBikeDescription("");
        bikePosition.getText().clear();
        viewModel.setBikePosition("");
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
}
