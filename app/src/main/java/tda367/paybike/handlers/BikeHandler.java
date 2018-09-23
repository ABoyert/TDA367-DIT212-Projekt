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
    public ArrayList<Bike> getAllBikes() {
        ArrayList<Bike> bikeList = new ArrayList<>();
        String[] pos = {"Testgatan 1"};

        for (DocumentSnapshot doc : db.read("bikes")) {
            bikeList.add(new Bike("bike1", 25.00, pos));
        }

        return bikeList;
    }


}
