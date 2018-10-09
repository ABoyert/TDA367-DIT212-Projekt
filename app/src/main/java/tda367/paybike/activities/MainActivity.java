package tda367.paybike.activities;

import android.content.Intent;
import android.net.Uri;
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

    private TextView registerNewUser;
    private Button findBikeBtn;

    // Firebase Auth
    private static final int RC_SIGN_IN = 123;

    private EditText userEmail1, userPassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBikeBtn = (Button) findViewById(R.id.findBikeBtn);
        registerNewUser = (TextView) findViewById(R.id.registerUser);

        // DO NOT DELETE
        DatabaseController db = DatabaseController.getInstance();

        // Open sign-in page
        //createSignInIntent();

        // EXAMPLE USE
        //UserHandler uhtest = UserHandler.getInstance();
        //uhtest.signIn("USERNAME", "PASSWORD");

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


    @Override
    public void onFragmentInteraction(Uri uri) {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d(TAG, "Current user: " + user.getDisplayName());
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}