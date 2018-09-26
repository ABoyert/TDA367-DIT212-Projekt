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

public class BikeHandler {
    // Get databaseController instance so we can use the database
    private static DatabaseController db = DatabaseController.getInstance();

    // Name of collection that holds all bikes, and fields in db
    private static final String BIKESCOLLECTION = "bikes";
    private static final String POSITION = "position";
    private static final String PRICE = "price";

    /*public BikeHandler() {
        Should class be a singleton or not?
    }*/

    // Returns all the objects in the bikes collection in FireStore
    public List<Bike> getAllBikes() {
        ArrayList<Bike> bikeList = new ArrayList<>();

        for (DocumentSnapshot doc : db.getCollection(BIKESCOLLECTION)) {
            bikeList.add(new Bike(
                    doc.getId(),
                    Double.parseDouble(doc.get(PRICE).toString()),
                    doc.get(POSITION).toString(),
                    true));
        }

        return bikeList;
    }

    // Take a bike object and put it into the database
    public void addBike(Bike bike) {
        // Put bike's properties into a Map that will be passed to the database
        Map<String, Object> bikeMap = new HashMap<>();
        bikeMap.put(POSITION, bike.getPosition());
        bikeMap.put(PRICE, bike.getPrice());

        db.add(BIKESCOLLECTION, bike.getId(), bikeMap);
    }

    // Take a bike object and remove it from the database
    public void deleteBike(Bike bike) {
        db.delete(BIKESCOLLECTION, bike.getId());
    }
}