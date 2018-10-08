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

import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tda367.paybike.R;
import tda367.paybike.database.DatabaseController;
import tda367.paybike.fragments.RegisterUserFragment;
import tda367.paybike.handlers.UserHandler;
import tda367.paybike.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity implements
        RegisterUserFragment.OnFragmentInteractionListener {

    // Set TAG to class name for use in debugging
    private static final String TAG = MainActivity.class.getSimpleName();

    // Enables login functionality
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    private TextView registerNewUser;
    private Button findBikeBtn;
    private EditText userEmail1, userPassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBikeBtn = (Button) findViewById(R.id.findBikeBtn);
        registerNewUser = (TextView) findViewById(R.id.registerUser);

        // DO NOT DELETE
        DatabaseController db = DatabaseController.getInstance();

        // TEST
        UserHandler uh = UserHandler.getInstance();
        Log.d(TAG, "Register success = " + uh.signIn("ponbac@student.chalmers.se", "test123"));

        findBikeBtn.setOnClickListener(v -> {
            UserHandler uh = UserHandler.getInstance();
            MainViewModel mvm = new MainViewModel();
            userEmail1 = (EditText) findViewById(R.id.userEmail);
            String userEmailValue = userEmail1.getText().toString();

            userPassword1 = (EditText) findViewById(R.id.userPassword);
            String userPasswordValue = userPassword1.getText().toString();
            if (userEmailValue.length() != 0 && userPasswordValue.length() != 0){
                if (uh.signIn(userEmailValue, userPasswordValue)){
                    startActivity(new Intent(this, BikeFeedActivity.class));
                }
            } else {
                Toast.makeText(MainActivity.this, mvm.getWarning(userEmailValue, userPasswordValue),
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
                            startActivity(new Intent(getApplicationContext(), BikeFeedActivity.class));
                            finish(); //WHEN THE DATABASE CONTROLLER IS REMOVED FROM THIS CLASS, THIS ROW
                            // SHOULD BE USED TO PREVENT LOGGED IN USERS TO USE BACK ARROW
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // SHOW TOAST OR SOMETHING ABOUT FAILURE
                        // MAYBE SHOW Exception e
                    }
                });
    }
}