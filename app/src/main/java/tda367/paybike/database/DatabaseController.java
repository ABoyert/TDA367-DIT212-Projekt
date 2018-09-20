package tda367.paybike.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Map;

//          -SINGLETON CLASS-
//  Use class by getting the class instance
//  through the function getInstance()
//
//  Class that is supposed to act as a
//  middleman between the application and
//  our google firestore database in order
//  to simplify use of the database
//
//  EXAMPLE DOCUMENT MAP:
//  Map<String, Object> example = new HashMap<>();
//  example.put("text", "abc");
//  example.put("name", "erik");
//  example.put("price", 22);
//
//  EXAMPLE USING addToDatabase:
//  addToDatabase("bikes", "bike1234", example); (This will add the example document map created above to the document 'bike1234' in collection 'bikes')

public class DatabaseController {

    // variable to hold the class instance (singleton)
    private static DatabaseController instance = null;
    // get firebase firestore db instance
    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    // TAG is used when logging, helpful for debugging
    private static final String TAG = DatabaseController.class.getSimpleName();

    protected DatabaseController() {
        // To make instantiation impossible outside of package and subclasses
    }

    // Create singleton if it does not exist, otherwise creates it.
    // Returns the DatabaseController instance.
    public static DatabaseController getInstance() {
        if (instance == null) {
            instance = new DatabaseController();
        }

        return instance;
    }

    // Add a new document with a given ID
    public void addToDatabase(String collection, String id, Map<String, Object> documentMap) {
        firestore.collection(collection).document(id)
                .set(documentMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    // Add a new document with a generated ID
    public void addToDatabase(String collection, Map<String, Object> documentMap) {
        firestore.collection(collection)
                .add(documentMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    // Read given field in given document and return as string
    public String readFromDatabase(final String collection, String documentID, String field) {
        DocumentReference docRef = firestore.collection(collection).document(documentID);

        Task<DocumentSnapshot> getDoc = docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.w(TAG, "get failed with ", task.getException());
                        }
                    }
                });

        while (!getDoc.isComplete()); // Bad code, need rework

        return getDoc.getResult().get(field).toString();
    }

    // Read given document and returns a DocumentSnapshot
    public DocumentSnapshot readFromDatabase(String collection, String documentID) {
        DocumentReference docRef = firestore.collection(collection).document(documentID);

        Task<DocumentSnapshot> getDoc = docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.w(TAG, "get failed with ", task.getException());
                        }
                    }
                });

        while (!getDoc.isComplete()); // Bad code, need rework

        return getDoc.getResult();
    }

    // Read given collection and return a list of document snapshots
    public List<DocumentSnapshot> readFromDatabase(final String collection) {
        Task<QuerySnapshot> getCollection = firestore.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Successfully read " + collection + "!");
                        } else {
                            Log.w(TAG, "Could not read " + collection + "! " + task.getException());
                        }
                    }
                });

        while (!getCollection.isComplete()); // Need rework badly

        return getCollection.getResult().getDocuments();
    }

    // Delete given document from db
    public void deleteFromDatabase(String collection, final String documentID) {
        firestore.collection(collection).document(documentID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "ID-" + documentID + " deleted!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "ID-" + documentID + " could not be deleted!");
                    }
                });
    }


}
