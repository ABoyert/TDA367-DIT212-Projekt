package tda367.paybike.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
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
import tda367.paybike.handlers.RequestHandler;
import tda367.paybike.model.Request;
import tda367.paybike.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity implements
        RegisterUserFragment.OnFragmentInteractionListener {

    // Set TAG to class name for use in debugging
    private static final String TAG = MainActivity.class.getSimpleName();

    // Enables login functionality
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    private TextView registerNewUser;
    private Button findBikeBtn;
    private EditText userEmail, userPassword;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBikeBtn = (Button) findViewById(R.id.findBikeBtn);
        registerNewUser = (TextView) findViewById(R.id.registerUser);

        // DO NOT DELETE
        DatabaseController db = DatabaseController.getInstance();

        // If already logged in, skip login screen!
        if (fAuth.getCurrentUser() != null) {
            Log.d(TAG, "Current User: " + fAuth.getCurrentUser().getDisplayName());

            showBikeFeed();
        }

        findBikeBtn.setOnClickListener(v -> {
            viewModel = new MainViewModel();
            userEmail = (EditText) findViewById(R.id.userEmail);
            userPassword = (EditText) findViewById(R.id.userPassword);

            String userEmailValue = userEmail.getText().toString();
            String userPasswordValue = userPassword.getText().toString();

            if (userEmailValue.length() != 0 && userPasswordValue.length() != 0){
                signIn(userEmailValue, userPasswordValue);
            } else {
                Toast.makeText(MainActivity.this, viewModel.getWarning(userEmailValue, userPasswordValue),
                        Toast.LENGTH_LONG).show();
            }
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

    // Tries to sign in user and starts BikeFeedActivity on success
    public void signIn(String email, String password) {
        // Create sign in-task
        Task<AuthResult> login = fAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d(TAG, "Successfully logged in " + fAuth.getCurrentUser().getDisplayName() + "!");
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
        startActivity(new Intent(getApplicationContext(), BikeFeedActivity.class));
        finish();
    }
}