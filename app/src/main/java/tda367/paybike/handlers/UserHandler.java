package tda367.paybike.handlers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import tda367.paybike.model.User;

/**
 * -SINGLETON CLASS-
 * Use class by getting the class instance
 * through the function getInstance()
 * <p>
 * Everything regarding users and user accounts in
 * Firebase Auth goes through this
 * class. Uses Firebase Auth in
 * order to do things regarding
 * users in Firebase.
 */

public class UserHandler {
    // TAG is used when logging, helpful for debugging
    private static final String TAG = UserHandler.class.getSimpleName();
    // Get FirebaseAuth instance
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    // Singleton variable
    private static UserHandler instance = null;

    // Private constructor
    private UserHandler() {

    }

    // Create singleton if it does not exist, otherwise return it.
    public static UserHandler getInstance() {
        if (instance == null) {
            instance = new UserHandler();
        }
        return instance;
    }

    // Supposed to return the current user as User-object (Now returning as FirebaseUser)
    // Return the current user as FirebaseUser-object
    public FirebaseUser getCurrentUser() {
        return fAuth.getCurrentUser();
    }

    // Tries to sign up the user and returns the signUp-task
    public Task<AuthResult> createUser(String email, String password, String name) {
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

        return signUp;
    }

    public void signOut() {
        fAuth.signOut();
    }

    public User convertGetCurrentUser() {
        FirebaseUser user = fAuth.getCurrentUser();
        if (user == null) return null;
        return new User(user.getEmail(), user.getDisplayName(), user.getUid());
    }

}