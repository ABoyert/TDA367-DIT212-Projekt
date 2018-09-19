package tda367.paybike.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseControllerTest {
    // Setup
    private static DatabaseController db;
    private static FirebaseFirestore firestore;
    private static Context appContext;


    @BeforeClass
    public static void setUp() throws Exception {
        // Get instance of databaseController to be used in following tests
        db = DatabaseController.getInstance();

        // Init Firebase
        appContext = InstrumentationRegistry.getTargetContext();
        FirebaseApp.initializeApp(appContext);

        // Get instance of firestore database
        firestore = FirebaseFirestore.getInstance();
    }

    @Test
    public void testAddToDatabase() {
        String collectionName = "tests";
        final String documentID = "testDoc";
        final String testText = "testingtesting";
        int expectedSize = 1;
        FieldValue timestamp = FieldValue.serverTimestamp();

        Map<String, Object> test = new HashMap<>();
        test.put("text", testText);
        test.put("timestamp", timestamp);

        // Add test to Firestore db using DatabaseController
        db.addToDatabase(collectionName, documentID, test);

        // Read collection tests into local collectionSnapshot
        Task<QuerySnapshot> collectionSnapshot = firestore.collection(collectionName)
                .get()
                /*.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Success in getting document!");
                        } else {
                            System.out.println("Error getting document: " + task.getException());
                        }
                    }
                })*/;

        while (!collectionSnapshot.isComplete()); // Nice code, need rework

        // Check if there is only the recently added document in collection tests
        assertEquals(expectedSize, collectionSnapshot.getResult().getDocuments().size());

        // Get test doc from tests collection snapshot
        DocumentSnapshot testDoc = collectionSnapshot.getResult().getDocuments().get(0);
        //Check if the document ID is correctly set
        assertEquals(documentID, testDoc.getId());
        //Check if text in document equals testText
        assertEquals(testText, testDoc.get("text"));
    }

    @Test
    public void testDeleteFromDatabase() {

    }

    @Test
    public void testReadFromDatabase() {

    }

    @AfterClass
    public static void tearDown() throws Exception {
        // Delete tests collection from firestore
        Task<QuerySnapshot> testsCollection = firestore.collection("tests")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            /*for (DocumentSnapshot doc : task.getResult()) {
                                firestore.collection("tests").document(doc.getId()).delete();
                            }*/
                        }
                    }
                });

        while (!testsCollection.isComplete()); // Nice nice, rework

        for (DocumentSnapshot doc : testsCollection.getResult()) {
            Task docDel = firestore.collection("tests").document(doc.getId())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("DOC DELETED!");
                        }
                    });

            while (!docDel.isSuccessful()); // Nice code again
        }
    }

}