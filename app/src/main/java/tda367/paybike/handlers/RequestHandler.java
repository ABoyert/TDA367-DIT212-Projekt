package tda367.paybike.handlers;

import android.net.Uri;

import com.google.firebase.firestore.DocumentSnapshot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tda367.paybike.database.DatabaseController;
import tda367.paybike.model.Request;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class RequestHandler {
    // Get databaseController instance so we can use the database
    private static DatabaseController db = DatabaseController.getInstance();

    private static final String REQUESTSCOLLECTION = "requests";
    private static final String SENDER = "sender";
    private static final String RENTABLEID = "rentable";
    private static final String ACCEPTED = "accepted";
    private static final String FROM_DATE_TIME = "fromDateTime:";
    private static final String TO_DATE_TIME = "toDateTime:";
    private static final String PRICE = "price:";

    private static RequestHandler instance = null;

    private RequestHandler() {
        // Singleton
    }

    // Create singleton if it does not exist, otherwise return it.
    public static RequestHandler getInstance() {
        if (instance == null) {
            instance = new RequestHandler();
        }

        return instance;
    }

    // Returns list of all requests in the database that is not accepted
    public List<Request> getCurrentRequests(){
        ArrayList<Request> requests = new ArrayList<>();

        // Might not be needed
        while (db.getCollection(REQUESTSCOLLECTION) == null);

        // for every request in the database, create new request-object
        for (DocumentSnapshot doc : db.getCollection(REQUESTSCOLLECTION)) {
            // Avoiding null pointer exception
            boolean accepted = doc.get(ACCEPTED) != null ? (Boolean) doc.get(ACCEPTED) : true;

            // Add all not yet accepted requests to the list
            if (!accepted) {
                requests.add(new Request(
                        doc.get(SENDER).toString(),
                        doc.get(RENTABLEID).toString(),
                        LocalDateTime.parse(doc.get(FROM_DATE_TIME).toString()),
                        LocalDateTime.parse(doc.get(TO_DATE_TIME).toString()),
                        Double.parseDouble(doc.get(PRICE).toString())));
            }
        }

        return requests;
    }

    // Add new request to the database
    public void add(Request request){
        // Put request's properties into a Map that will be passed to the database
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put(SENDER, request.getSendingUserId());
        requestMap.put(RENTABLEID, request.getTargetRentableId());
        requestMap.put(ACCEPTED, request.isAccepted());
        requestMap.put(FROM_DATE_TIME, request.getFromDateTime());
        requestMap.put(TO_DATE_TIME, request.getToDateTime());
        requestMap.put(PRICE, request.getPrice());

        db.add(REQUESTSCOLLECTION, requestMap);
    }
}
