package tda367.paybike.handlers;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tda367.paybike.database.DatabaseController;
import tda367.paybike.model.User;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class UserHandler {
    // TAG is used when logging, helpful for debugging
    private static final String TAG = UserHandler.class.getSimpleName();
    // Get FirebaseAuth instance
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    // Supposed to return the current user as User-object (Now returning as FirebaseUser)
    public FirebaseUser getCurrentUser() {
        FirebaseUser fUser = fAuth.getCurrentUser();

        if(fAuth.getCurrentUser() != null)
            return fUser;

        return null;
    }

    // Tries to sign in the user and returns true on success
    public boolean signIn(String email, String password) {
        // Create sign in-task
        Task<AuthResult> auth = fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            FirebaseUser user = fAuth.getCurrentUser();
                            Log.d(TAG, "Sign in with e-mail success! User: " + user.getDisplayName());
                        } else {
                            // Sign in fails
                            Log.w(TAG, "Sign in with e-mail FAILED! " + task.getException());
                        }
                    }
                });

        // Give the sign in some time to finish
        SystemClock.sleep(500);

        if (auth.isComplete() && auth.isSuccessful()) {
            return true;
        } else if (auth.isComplete() && !auth.isSuccessful()) {
            return false;
        }

        // Give it some additional time if not done
        SystemClock.sleep(1000);

        if (auth.isComplete() && auth.isSuccessful()) {
            return true;
        } else {
            return false;
        }
    }
}
