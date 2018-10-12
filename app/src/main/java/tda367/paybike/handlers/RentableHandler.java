package tda367.paybike.handlers;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tda367.paybike.R;
import tda367.paybike.database.DatabaseController;
import tda367.paybike.model.Bike;
import tda367.paybike.model.Rentable;
import tda367.paybike.model.RentableFactory;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class RentableHandler {
    // Get databaseController instance so we can use the database
    private static DatabaseController db = DatabaseController.getInstance();
    private RentableFactory factory = new RentableFactory();

    // TAG is used when logging, helpful for debugging
    private static final String TAG = RentableHandler.class.getSimpleName();

    // Name of collection that holds all bikes, and fields in db
    private static final String BIKESCOLLECTION = "bikes";
    private static final String POSITION = "position";
    private static final String PRICE = "price";
    private static final String IMAGE = "image";
    private static final String DESCRIPTION = "description";
    private static final String NAME = "name";
    private static final String OWNER = "owner";
    private static final String AVAILABLE = "available";

    private static RentableHandler instance = null;

    private RentableHandler() {
        // Singleton
    }

    // Create singleton if it does not exist, otherwise return it.
    public static RentableHandler getInstance() {
        if (instance == null) {
            instance = new RentableHandler();
        }

        return instance;
    }

    // Returns all the objects in the bikes collection in FireStore
    public List<Rentable> getAllRentables() {
        ArrayList<Rentable> rentablesList = new ArrayList<>();

        /*for (DocumentSnapshot doc : db.getCollection(BIKESCOLLECTION)) {
            rentablesList.add(RentableFactory.createRentable("Bike",
                    (String) doc.get(NAME),
                    Double.parseDouble(doc.get(PRICE).toString()),
                    (String) doc.get(POSITION),
                    doc.get(AVAILABLE) == null ? false : (Boolean) doc.get(AVAILABLE),
                    (String) doc.get(OWNER),
                    doc.get(IMAGE) == null ? null : Uri.parse(doc.get(IMAGE).toString()),
                    (String) doc.get(DESCRIPTION),
                    doc.getId()));
        }*/
        for(int i = 0; i < 10; i++) {
            rentablesList.add(RentableFactory.createRentable("Bike",
                    "Test" + i ,
                    i * 5,
                    "Testgatan",
                    true,
                    "Kalle",
                    null,
                    "Jättefin",
                    "24141" + i));
        }

        return rentablesList;
    }

    // Take a bike object and put it into the database
    public void addRentable(Rentable rentable) {
        // Put bike's properties into a Map that will be passed to the database
        Map<String, Object> bikeMap = new HashMap<>();
        bikeMap.put(POSITION, rentable.getPosition());
        bikeMap.put(PRICE, rentable.getPrice());
        bikeMap.put(IMAGE, waitForTask(db.uploadToStorage(rentable.getImagePath())).toString());
        //Log.d(TAG, "IMAGE = " + waitForTask(db.uploadToStorage(rentable.getImagePath())));
        bikeMap.put(DESCRIPTION, rentable.getDescription());
        bikeMap.put(NAME, rentable.getName());
        bikeMap.put(OWNER, rentable.getOwner());
        bikeMap.put(AVAILABLE, rentable.isAvailable());

        db.add(BIKESCOLLECTION, bikeMap);

    }

    private Uri waitForTask(Task<Uri> task) {
        while (!task.isSuccessful()) {
            //SystemClock.sleep(50);
            Log.d(TAG, "TASK NOT DONE YET!");
        }

        Log.d(TAG, "TASK DONE!");
        return task.getResult();
    }

    // Take a bike object and remove it from the database
    public void deleteRentable(Rentable rentable) {
        db.delete(BIKESCOLLECTION, rentable.getId());

    }

    public Rentable createRentableWithFactory(boolean withID, String type, String name, double price,
                                              String pos, boolean available, String owner,
                                              Uri imagelink, String description, String id){

        if(withID == true)return factory.createRentable(type, name, price, pos, available, owner, imagelink, description,id);

        if(withID == false)return factory.createRentableNoID(type, name, price, pos, available, owner, imagelink, description);

        return null;
    }
}