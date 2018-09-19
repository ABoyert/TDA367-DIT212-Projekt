package tda367.paybike.database;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DatabaseControllerTest {
    private static DatabaseController db;
    private static FirebaseFirestore firestore;

    @BeforeClass
    public static void setUp() throws Exception {
        // Get instance of databaseController to be used in following tests
        db = DatabaseController.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    @Test
    public void testAddToDatabase() {
        String collectionName = "tests";
        final String documentID = "testDoc";
        final String testText = "testingtesting";
        FieldValue timestamp = FieldValue.serverTimestamp();

        Map<String, Object> test = new HashMap<>();
        test.put("text", testText);
        test.put("timestamp", timestamp);

        // Add test to Firestore db using DatabaseController
        db.addToDatabase(collectionName, documentID, test);

        // Read tests collection to test that the document is correctly in the database
        firestore.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot testDoc = task.getResult().getDocuments().get(0);

                            // Check if there is only the recently added document in collection tests
                            int size = task.getResult().getDocuments().size();
                            assertEquals(1, size);

                            //Check if the document ID is correctly set
                            assertEquals(documentID, testDoc.getId());

                            //Check if text in document equals testText
                            assertEquals(testText, testDoc.get("text"));
                        } else {
                            System.out.println("Error getting document: " + task.getException());
                        }
                    }
                });
    }

    @Test
    public void testDeleteFromDatabase() {

    }

    @Test
    public void testReadFromDatabase() {

    }

    @AfterClass
    public static void tearDown() throws Exception {

    }
}