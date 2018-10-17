package tda367.paybike.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import tda367.paybike.R;
import tda367.paybike.database.DatabaseController;
import tda367.paybike.fragments.RegisterUserFragment;
import tda367.paybike.viewmodels.AddBikeViewModel;
import tda367.paybike.viewmodels.LoginViewModel;

/*
 * Created by Julia Gustafsson, Anton Boyert and Pontus Backman
 *
 * This Activity enables the user to log in to the application or alternatively,
 * register a new account. New registrations are handled by RegisterUserFragment.
 * Launch activity when the app starts for the first time. Will be skipped if a user is already logged in.
 *
 * General note: All activities work in close relation with their respective ViewModel which holds the data to be shown,
 * while the activity itself is responsible for displaying it in the correct fashion.
 *
 * This activity shares its ViewModel with the RegisterUserFragment which allows them to share information.
 */

public class LoginActivity extends AppCompatActivity implements
        RegisterUserFragment.OnFragmentInteractionListener {

    /* Constants */
    private static final String TAG = LoginActivity.class.getSimpleName(); // Used for logging

    /* Widgets */
    private TextView registerNewUser;
    private Button findBikeBtn;
    private EditText userEmail, userPassword;

    /* Resources */
    private FirebaseAuth fAuth = FirebaseAuth.getInstance(); // Used to verify user credentials
    private LoginViewModel viewModel;

    /* Automatically called when Acitivy is created */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        /* If already logged in, skip login screen! */
        if (fAuth.getCurrentUser() != null) {
            Log.d(TAG, "Current User: " + fAuth.getCurrentUser().getDisplayName());
            viewModel.getC().updateCurrentUser();
            showBikeFeed();
        }

        setContentView(R.layout.activity_main);
        //viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        /* Configure widgets */
        findBikeBtn = findViewById(R.id.findRentableBtn);
        registerNewUser = findViewById(R.id.registerUser);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);

        /* DO NOT DELETE */
        DatabaseController db = DatabaseController.getInstance();

        /* If already logged in, skip login screen! */
        if (fAuth.getCurrentUser() != null) {
            Log.d(TAG, "Current User: " + fAuth.getCurrentUser().getDisplayName());
            showBikeFeed();
        }

        /* Handle click events on login button */
        findBikeBtn.setOnClickListener(v -> {
            String userEmailValue = userEmail.getText().toString();
            String userPasswordValue = userPassword.getText().toString();

            if (userEmailValue.length() != 0 && userPasswordValue.length() != 0){
                signIn(userEmailValue, userPasswordValue);
            } else {
                Toast.makeText(LoginActivity.this, viewModel.getWarning(userEmailValue, userPasswordValue),
                        Toast.LENGTH_LONG).show();
            }
        });

        registerNewUser.setOnClickListener(view -> registerNewUser(view));
    }

    @Override
    public void onUserRegistration() {
        if (viewModel.createUser()) {
            getFragmentManager().popBackStack();
        } else {
            Toast.makeText(this,
                    "Unable to register user at the moment. Please try again later.",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void registerNewUser(View view) {
        RegisterUserFragment newUser = RegisterUserFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_frame, newUser, "NEW_USER_FRAGMENT").commit();
    }

    // Tries to sign in user and starts BikeFeedActivity on success
    public void signIn(String email, String password) {
        LoginViewModel viewModel = new LoginViewModel();
        // Create sign in-task
        Task<AuthResult> login = fAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d(TAG, "Successfully logged in " + fAuth.getCurrentUser().getDisplayName() + "!");
                        viewModel.getC().updateCurrentUser();
                        showBikeFeed();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // SHOW TOAST OR SOMETHING ABOUT FAILURE
                        // MAYBE SHOW Exception e
                        Log.w(TAG, "LOGIN FAILED! Error: " + e.getMessage());
                        Toast.makeText(getApplicationContext(), "Error! " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    // Starts the BikeFeed activity
    private void showBikeFeed() {
        startActivity(new Intent(getApplicationContext(), RentableFeedActivity.class));
        finish();
    }
}