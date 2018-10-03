package tda367.paybike.handlers;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tda367.paybike.database.DatabaseController;
import tda367.paybike.model.Bike;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class RentableHandler {
    // Get databaseController instance so we can use the database
    private static DatabaseController db = DatabaseController.getInstance();

    // Name of collection that holds all bikes, and fields in db
    private static final String BIKESCOLLECTION = "bikes";
    private static final String POSITION = "position";
    private static final String PRICE = "price";
    private static final String IMAGE = "image";
    private static final String DESCRIPTION = "description";
    private static final String NAME = "name";
    private static final String OWNER = "owner";
    private static final String AVAILABLE = "available";

    /*public RentableHandler() {
        Should class be a singleton or not?
    }*/

    // Returns all the objects in the bikes collection in FireStore
    public List<Bike> getAllBikes() {
        ArrayList<Bike> bikeList = new ArrayList<>();

        for (DocumentSnapshot doc : db.getCollection(BIKESCOLLECTION)) {
            bikeList.add(new Bike(
                    (String) doc.get(NAME),
                    Double.parseDouble(doc.get(PRICE).toString()),
                    (String) doc.get(POSITION),
                    ((doc.get(AVAILABLE) == null) ? false : (Boolean) doc.get(AVAILABLE)),
                    (String) doc.get(OWNER),
                    (String) doc.get(IMAGE),
                    (String) doc.get(DESCRIPTION),
                    doc.getId()));
        }

        return bikeList;
    }

    // Take a bike object and put it into the database
    public void addBike(Bike bike) {
        // Put bike's properties into a Map that will be passed to the database
        Map<String, Object> bikeMap = new HashMap<>();
        bikeMap.put(POSITION, bike.getPosition());
        bikeMap.put(PRICE, bike.getPrice());
        bikeMap.put(IMAGE, bike.getImageLink());
        bikeMap.put(DESCRIPTION, bike.getDescription());
        bikeMap.put(NAME, bike.getName());
        bikeMap.put(OWNER, bike.getOwner());
        bikeMap.put(AVAILABLE, bike.isAvailable());

        db.add(BIKESCOLLECTION, bikeMap);
    }

    // Take a bike object and remove it from the database
    public void deleteBike(Bike bike) {
        db.delete(BIKESCOLLECTION, bike.getId());
    }
}