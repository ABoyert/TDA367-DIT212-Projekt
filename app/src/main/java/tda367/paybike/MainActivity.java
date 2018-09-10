package tda367.paybike;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // Set TAG to class name for use in debugging
    private static final String TAG = MainActivity.class.getSimpleName();
    // Get FireStore database instance
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pushTestUser("John", "Doe", 1973);
        //readMostRecentUser(); Called in pushTestUser on success
    }

    // Example reading from database
    private void readMostRecentUser() {
        db.collection("users").orderBy("usercreated", Direction.DESCENDING).limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot mostRecentUser = task.getResult().getDocuments().get(0);
                            Log.d(TAG, "MOST RECENTLY ADDED USER: " + mostRecentUser.getData() + " ID = " + mostRecentUser.getId());
                        } else {
                            Log.w(TAG, "Error getting document: " + task.getException());
                        }
                    }
                });

        //return mostRecentUser;
    }

    // Example adding to database
    private void pushTestUser(String firstName, String lastName, int birthYear) {
        Map<String, Object> user = new HashMap<>();
        user.put("firstname", firstName);
        user.put("lastname", lastName);
        user.put("birthyear", birthYear);
        user.put("usercreated", FieldValue.serverTimestamp());

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        readMostRecentUser();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Failure when pushing user:" + e);
                    }
                });
    }
}