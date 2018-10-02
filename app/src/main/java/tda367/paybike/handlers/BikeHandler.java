package tda367.paybike.handlers;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.database.DatabaseController;
import tda367.paybike.model.Bike;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class BikeHandler {
    // Get databaseController instance so we can use the database
    private static DatabaseController db = DatabaseController.getInstance();

    /*public BikeHandler() {
        Should class be a singleton or not?
    }*/

    // Can finish function when the Bike class is done
    public List<Bike> getAllBikes() {
        ArrayList<Bike> bikeList = new ArrayList<>();

        for (DocumentSnapshot doc : db.getCollection("bikes")) {
            bikeList.add(new Bike(
                    doc.getId(),
                    Double.parseDouble(doc.get("price").toString()),
                    doc.get("position").toString(),
                    true));
        }


        /*for (int i = 0; i < 10; i++) {
            bikeList.add(new Bike("bike" + i, 5*i, "Testgatan " + i, true));
        }*/

        return bikeList;
    }

    public void deleteBike(Bike bike){

    }

    public void adBike(Bike bike){

    }
}