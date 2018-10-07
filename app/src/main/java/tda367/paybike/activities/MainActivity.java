package tda367.paybike.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

import tda367.paybike.R;
import tda367.paybike.database.DatabaseController;
import tda367.paybike.fragments.BikeDetailsFragment;
import tda367.paybike.fragments.RegisterUserFragment;
import tda367.paybike.handlers.UserHandler;
import tda367.paybike.model.Bike;

public class MainActivity extends AppCompatActivity implements
        RegisterUserFragment.OnFragmentInteractionListener {

    // Set TAG to class name for use in debugging
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView registerNewUser;
    private Button findBikeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBikeBtn = (Button) findViewById(R.id.findBikeBtn);
        registerNewUser = (TextView) findViewById(R.id.registerUser);

        // DO NOT DELETE
        DatabaseController db = DatabaseController.getInstance();

        findBikeBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, BikeFeedActivity.class));
            // finish(); WHEN THE DATABASE CONTROLLER IS REMOVED FROM THIS CLASS, THIS ROW
            // SHOULD BE USED TO PREVENT LOGGED IN USERS TO USE BACK ARROW
        });

        registerNewUser.setOnClickListener(view -> registerNewUser(view));
    }

    private void registerNewUser(View view) {
        RegisterUserFragment newUser = RegisterUserFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_frame, newUser, "NEW_USER_FRAGMENT").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}