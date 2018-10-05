package tda367.paybike.handlers;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

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
        Task<AuthResult> login = fAuth.signInWithEmailAndPassword(email, password)
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

        if (login.isComplete() && login.isSuccessful()) {
            return true;
        } else if (login.isComplete() && !login.isSuccessful()) {
            return false;
        }

        // Give it some additional time if not done
        SystemClock.sleep(1000);

        if (login.isComplete() && login.isSuccessful()) {
            return true;
        } else {
            return false;
        }
    }

    // Tries to sign up the user and returns true on success
    public boolean createUser(String email, String password, String name) {
        // Create sign in-task
        Task<AuthResult> signUp = fAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success
                            FirebaseUser user = fAuth.getCurrentUser();
                            Log.d(TAG, "Sign up with e-mail success! E-mail: " + user.getEmail());

                            // Set profile info
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "Name set! Display name: " + user.getDisplayName());
                                            }
                                        }
                                    });
                        } else {
                            // Sign up fails
                            Log.w(TAG, "Sign up with e-mail FAILED! " + task.getException());
                        }
                    }
                });

        // Give the sign up some time to finish
        SystemClock.sleep(500);

        if (signUp.isComplete() && signUp.isSuccessful()) {
            return true;
        } else if (signUp.isComplete() && !signUp.isSuccessful()) {
            return false;
        }

        // Give it some additional time if not done
        SystemClock.sleep(1000);

        if (signUp.isComplete() && signUp.isSuccessful()) {
            return true;
        } else {
            return false;
        }
    }
}
