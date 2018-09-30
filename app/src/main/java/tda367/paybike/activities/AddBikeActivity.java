package tda367.paybike.activities;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tda367.paybike.R;
import tda367.paybike.model.Bike;
import tda367.paybike.viewmodels.AddBikeViewModel;

public class AddBikeActivity extends AppCompatActivity {

    private static final int MAX_PRICE = 1000;
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    private static final int MAX_TITLE_LENGTH = 50;

    private static final String INVALID_NAME = "Bike title cannot exceed " + MAX_TITLE_LENGTH + " characters.";
    private static final String INVALID_POSITION = "Invalid address format.";
    private static final String INVALID_DESCRIPTION = "Description cannot exceed " + MAX_DESCRIPTION_LENGTH + " characters.";
    private static final String INVALID_PRICE = "Price cannot exceed $" + MAX_PRICE;

    private Button addBikeBtn;
    private EditText bikeName, bikeDescription, bikePosition, bikePrice;

    private AddBikeViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);

        model = ViewModelProviders.of(this).get(AddBikeViewModel.class);

        addBikeBtn = (Button) findViewById(R.id.postBikeBtn);
        bikeName = (EditText) findViewById(R.id.nameText);
        bikeDescription = (EditText) findViewById(R.id.descriptionText);
        bikePosition = (EditText) findViewById(R.id.positionText);
        bikePrice = (EditText) findViewById(R.id.priceText);

        addBikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postBike();
            }
        });

    }

    private boolean inputIsValid() {

        return false;
    }

    private void addBike() {

    }

    private boolean nameLengthIsValid() {

        return bikeName.getText().length() < 50? true : false;
    }

    private boolean priceIsValid() {
        return bikePrice.getText().length() < 4 ? true : false;
    }

    private boolean descriptionIsValid() {
        return bikeDescription.getText().length() < 1000 ? true : false;
    }

    private boolean positionIsValid() {
        //TODO Write method to check this
        return false;
    }

    private void postBike() {
        Bike newBike = new Bike(bikeName.getText().toString(),
                Double.parseDouble(bikePrice.getText().toString()),
                bikePosition.getText().toString(),
                true,
                "owner",
                "imageLink",
                bikeDescription.getText().toString());

        model.postBike(newBike);
    }


}
