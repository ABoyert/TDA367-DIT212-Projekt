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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import tda367.paybike.R;
import tda367.paybike.database.DatabaseController;
import tda367.paybike.viewmodels.AddBikeViewModel;

public class AddBikeActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 71;
    private static final ColorStateList COLOR_CORRECT = ColorStateList.valueOf(Color.parseColor("#30d9af"));
    private static final ColorStateList COLOR_WRONG = ColorStateList.valueOf(Color.parseColor("#e96e6e"));

    private Button addBikeBtn;
    private EditText bikeName, bikeDescription, bikePosition, bikePrice;
    private TextView warning;
    private ImageButton chooseImageBtn;

    private Uri filePath;
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

        bikeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setBikeName(bikeName.getText().toString());
                boolean valid = viewModel.nameIsValid() ? true : false;
                setBackgroundTintListColor(valid, bikeName);
                updatePostBikeBtn();}
        });

        bikeDescription = (EditText) findViewById(R.id.descriptionText);
        bikeDescription.setText(viewModel.getBikeDescription());
        bikeDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setBikeDescription(bikeDescription.getText().toString());
                boolean valid = viewModel.descriptionIsValid() ? true : false;
                setBackgroundTintListColor(valid, bikeDescription);
                updatePostBikeBtn();}
        });

        // TODO Check if this needs to be updated when we have a position object to store addresses
        bikePosition = (EditText) findViewById(R.id.positionText);
        bikePosition.setText(viewModel.getBikeDescription());
        bikePosition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setBikePosition(bikePosition.getText().toString());
                boolean valid = viewModel.positionIsValid() ? true : false;
                setBackgroundTintListColor(valid, bikePosition);
                updatePostBikeBtn();
            }
        });

        bikePrice = (EditText) findViewById(R.id.priceText);
        bikePrice.setText(viewModel.getBikePrice());
        bikePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setBikePrice(bikePrice.getText().toString());
                boolean valid = viewModel.priceIsValid() ? true : false;
                setBackgroundTintListColor(valid, bikePrice);
                updatePostBikeBtn();
            }
        });

        chooseImageBtn = (ImageButton) findViewById(R.id.choseImage);
        chooseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        
        warning = (TextView) findViewById(R.id.inputInvalidText);

        addBikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.inputIsValid()) {
                    viewModel.postBike();
                    viewModel.clearAll();
                    finish(); // End Activity
                }
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                chooseImageBtn.setImageBitmap(bitmap);
                viewModel.setBikeImagePath(filePath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setBackgroundTintListColor(boolean valid, EditText editText) {
        if (valid) {
            ViewCompat.setBackgroundTintList(editText, COLOR_CORRECT);
        } else {
            ViewCompat.setBackgroundTintList(editText, COLOR_WRONG);
        }
    }

    private void updatePostBikeBtn() { addBikeBtn.setEnabled(viewModel.inputIsValid()); }

    private void updateModel() {
        viewModel.setBikeName(bikeName.getText().toString());
        viewModel.setBikeDescription(bikeDescription.getText().toString());
        viewModel.setBikePosition(bikePosition.getText().toString());
        viewModel.setBikePrice(bikePrice.getText().toString());
        viewModel.setBikeImagePath(filePath);
    }

}
