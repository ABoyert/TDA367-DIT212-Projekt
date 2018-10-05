package tda367.paybike.handlers;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Rentable> getAllRentables() {
        ArrayList<Rentable> rentablesList = new ArrayList<>();

        for (DocumentSnapshot doc : db.getCollection(BIKESCOLLECTION)) {
            rentablesList.add(RentableFactory.createRentable("Bike",
                    (String) doc.get(NAME),
                    Double.parseDouble(doc.get(PRICE).toString()),
                    (String) doc.get(POSITION),
                    ((doc.get(AVAILABLE) == null) ? false : (Boolean) doc.get(AVAILABLE)),
                    (String) doc.get(OWNER),
                    (String) doc.get(IMAGE),
                    (String) doc.get(DESCRIPTION),
                    doc.getId()));
        }

        return rentablesList;
    }

    // Take a bike object and put it into the database
    public void addRentable(Rentable rentable) {
        // Put bike's properties into a Map that will be passed to the database
        Map<String, Object> bikeMap = new HashMap<>();
        bikeMap.put(POSITION, rentable.getPosition());
        bikeMap.put(PRICE, rentable.getPrice());
        bikeMap.put(IMAGE, rentable.getImageLink());
        bikeMap.put(DESCRIPTION, rentable.getDescription());
        bikeMap.put(NAME, rentable.getName());
        bikeMap.put(OWNER, rentable.getOwner());
        bikeMap.put(AVAILABLE, rentable.isAvailable());

        db.add(BIKESCOLLECTION, bikeMap);
    }

    // Take a bike object and remove it from the database
    public void deleteRentable(Rentable rentable) {
        db.delete(BIKESCOLLECTION, rentable.getId());

    }

    public Rentable createRentableWithFactory(boolean withID, String type, String name, double price,
                                              String pos, boolean available, String owner,
                                              String imagelink, String description, String id){

        if(withID == true)return factory.createRentable(type, name, price, pos, available, owner, imagelink, description,id);

        if(withID == false)return factory.createRentableNoID(type, name, price, pos, available, owner, imagelink, description);

        return null;
    }
}