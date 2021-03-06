package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;

import tda367.paybike.R;
import tda367.paybike.viewmodels.AddBikeViewModel;

/*
 * Created by Julia Gustafsson
 *
 * This Activity gives the user the ability to add their bikes to the application for rental.
 *
 * General note: All activities work in close relation with their respective ViewModel which holds the data to be shown,
 * while the activity itself is responsible for displaying it in the correct fashion.
 */

public class AddBikeActivity extends AppCompatActivity {

    /* Constants */
    private static final int PICK_IMAGE_REQUEST = 71; // Request id, used for fetching images from device
    private static final String TAG = AddBikeActivity.class.getSimpleName(); // Used for logging
    private final ColorStateList COLOR_CORRECT = ColorStateList.valueOf(Color.parseColor("#30d9af"));
    private final ColorStateList COLOR_WRONG = ColorStateList.valueOf(Color.parseColor("#e96e6e"));

    /* Widgets */
    private Button addBikeBtn;
    private EditText bikeName,
            bikeDescription,
            bikePrice,
            street,
            zipcode,
            city;
    private ImageButton chooseImageBtn;

    /* Resources */
    private Uri filePath;
    private AddBikeViewModel viewModel;

    /* Automatically called when Activity is created */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rentable);

        viewModel = ViewModelProviders.of(this).get(AddBikeViewModel.class);

        /* Configure bikeName */
        bikeName = findViewById(R.id.nameText);
        bikeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                /* Notify ViewModel of changes in text */
                viewModel.setBikeName(bikeName.getText().toString());
                /* Check if input is valid (see AddBikeViewModel for constraints) */
                boolean valid = viewModel.nameIsValid();
                setBackgroundTintListColor(valid, bikeName);
                /* Evaluate if button should be enabled or disabled */
                updatePostBikeBtn();
            }
        });

        /* Configure bikeDescription */
        bikeDescription = findViewById(R.id.descriptionText);
        bikeDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Notify ViewModel of changes in text
                viewModel.setBikeDescription(bikeDescription.getText().toString());
                // Check if input is valid (see AddBikeViewModel for constraints)
                boolean valid = viewModel.descriptionIsValid();
                setBackgroundTintListColor(valid, bikeDescription);
                // Evaluate if button should be enabled or disabled
                updatePostBikeBtn();
            }
        });

        /* Configure street */
        street = findViewById(R.id.street);
        street.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                /* Notify ViewModel of changes in text */
                viewModel.setStreet(street.getText().toString());
                /* Check if input is valid (see AddBikeViewModel for constraints) */
                boolean valid = viewModel.streetIsValid();
                setBackgroundTintListColor(valid, street);
                /* Evaluate if button should be enabled or disabled */
                updatePostBikeBtn();
            }
        });

        /* Configure zipcode */
        zipcode = findViewById(R.id.zipcode);
        zipcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                /* Notify ViewModel of changes in text */
                viewModel.setZipcode(zipcode.getText().toString());
                /* Check if input is valid (see AddBikeViewModel for constraints) */
                boolean valid = viewModel.zipcodeIsValid();
                setBackgroundTintListColor(valid, zipcode);
                /* Evaluate if button should be enabled or disabled */
                updatePostBikeBtn();
            }
        });

        /* Configure city */
        city = findViewById(R.id.city);
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                /* Notify ViewModel of changes in text */
                viewModel.setCity(city.getText().toString());
                /* Check if input is valid (see AddBikeViewModel for constraints) */
                boolean valid = viewModel.cityIsValid();
                setBackgroundTintListColor(valid, city);
                /* Evaluate if button should be enabled or disabled */
                updatePostBikeBtn();
            }
        });

        /* Configure bikePrice */
        bikePrice = findViewById(R.id.priceText);
        bikePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                /* Notify ViewModel of changes in text */
                viewModel.setBikePrice(bikePrice.getText().toString());
                /* Check if input is valid (see AddBikeViewModel for constraints) */
                boolean valid = viewModel.priceIsValid();
                setBackgroundTintListColor(valid, bikePrice);
                /* Evaluate if button should be enabled or disabled */
                updatePostBikeBtn();
            }
        });

        /* Configure chooseImageBtn */
        chooseImageBtn = findViewById(R.id.chooseImage);
        chooseImageBtn.setOnClickListener(v -> chooseImage());

        /* Configure addBikeBtn */
        addBikeBtn = findViewById(R.id.postRentableBtn);
        addBikeBtn.setFocusable(true);
        /* If button is pressed, check if input is valid before posting bike and clear all fields. */
        addBikeBtn.setOnClickListener(v -> {
            if (viewModel.inputIsValid()) {
                viewModel.postRentable();
                viewModel.clearAll();
                finish(); // End Activity when bike is added
            }
        });
    }

    /* Fetches last values from ViewModel, if activity is paused and then resumed */
    @Override
    public void onResume() {
        super.onResume();
        bikeName.setText(viewModel.getBikeName());
        bikeDescription.setText(viewModel.getBikeDescription());
        street.setText(viewModel.getStreet());
        zipcode.setText(viewModel.getZipcode());
        city.setText(viewModel.getCity());
        bikePrice.setText(viewModel.getBikePrice());
        if (!(viewModel.getBikeImagePath() == null)) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), viewModel.getBikeImagePath());
                chooseImageBtn.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updatePostBikeBtn();
    }

    /*
    Automatically called when an image is chosen by the user as a result of a call to chooseImage()
    Method is responsible for storing the image in the ViewModel as well as displaying it to the user
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                chooseImageBtn.setImageBitmap(bitmap);
                viewModel.setBikeImagePath(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* Starts a new intent which allows the user to select an image from their Android device */
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    /* Updates the color of the EditText underline according to validation of field */
    private void setBackgroundTintListColor(boolean valid, EditText editText) {
        if (valid) {
            ViewCompat.setBackgroundTintList(editText, COLOR_CORRECT);
        } else {
            ViewCompat.setBackgroundTintList(editText, COLOR_WRONG);
        }
    }

    /*
    Decides if the button should be enabled or disabled depending on the validation of the input fields
    See ViewModel for constraints
    */
    private void updatePostBikeBtn() {
        addBikeBtn.setEnabled(viewModel.inputIsValid());
    }

}
