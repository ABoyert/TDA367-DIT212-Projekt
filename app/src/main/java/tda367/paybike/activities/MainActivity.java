package tda367.paybike.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import tda367.paybike.R;
import tda367.paybike.database.DatabaseController;
import tda367.paybike.handlers.UserHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Set TAG to class name for use in debugging
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button findBikeBtn;

    // Firebase Auth
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBikeBtn = (Button) findViewById(R.id.findBikeBtn);

        // This line need to be here atm (I think)
        DatabaseController db = DatabaseController.getInstance();

        // Open sign-in page
        //createSignInIntent();

        // EXAMPLE USE
        UserHandler uh = new UserHandler();
        uh.signIn("USERNAME", "PASSWORD");

        findBikeBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, BikeFeedActivity.class));
        });
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * The two following methods are creating a sign-in intent and handling the result using FirebaseAuthUI-plugin
     */

    /*public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_create_intent]
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
    }*/
}