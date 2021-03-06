package tda367.paybike.repositori;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tda367.paybike.handlers.RentableHandler;
import tda367.paybike.handlers.RequestHandler;
import tda367.paybike.handlers.UserHandler;
import tda367.paybike.model.PayBike;
import tda367.paybike.model.Position;
import tda367.paybike.model.Rentable;
import tda367.paybike.model.RentableFactory;
import tda367.paybike.model.Request;
import tda367.paybike.model.User;

import static java.util.stream.Collectors.toCollection;

public class Repository {

    /*
     *
     * Passes data
     *
     * */
    private PayBike payBike = PayBike.getInstance();
    private RequestHandler requestHandler = RequestHandler.getInstance();
    private RentableHandler rentableHandler = RentableHandler.getInstance();
    private UserHandler userHandler = UserHandler.getInstance();

    // Set TAG to class name for use in debugging
    private static final String TAG = Repository.class.getSimpleName();

    public Repository() {

    }

    //Retrieves rentables from database
    public List<Rentable> getDatabaseRentables() {
        return rentableHandler.getAllRentables();
    }

    public List<Request> getDatabaseRequests() {
        return requestHandler.getCurrentRequests();
    }

    /* Returns requests that are associated with the current user */
    public List<Request> getCurrentUserRequests() {
        updateModelRequests();
        return getModelRequests().stream()
                .filter(request -> getCurrentUser().getUserID().equals(request.getSendingUserId()) ||
                        getCurrentUser().getUserID().equals(payBike.getRentableFromId(request.getTargetRentableId()).getOwner()))
                .collect(toCollection(ArrayList::new));
    }

    public void updateModelRequests() {
        payBike.setModelRequests(getDatabaseRequests());
    }

    public List<Request> updateAndGetRequests() {
        updateModelRequests();
        return getModelRequests();
    }

    //Puts rentables from database in payBike
    public void updateModelRentables() {
        payBike.setModelRentables(getDatabaseRentables());
    }

    //Get list of rentables from payBike
    public List<Rentable> getModelRentables() {
        return payBike.getModelRentables();
    }

    public List<Request> getModelRequests() {
        return payBike.getModelRequests();
    }

    //Updates the payBike with rentables from the database and returns the updated list
    public List<Rentable> updateAndGetRentables() {
        updateModelRentables();
        return getModelRentables();
    }

    public void deleteRentable(Rentable rentable) {
        rentableHandler.deleteRentable(rentable);

        for (Request r : payBike.getModelRequests()) {
            if (r.getTargetRentableId().equals(rentable.getId())) {
                requestHandler.deleteRequest(r);
            }
        }
    }

    public void deleteRequest(Request request) {
        requestHandler.deleteRequest(request);
    }

    public void updateRentable(Rentable rentable) {
        rentableHandler.updateRentable(rentable);
    }

    public void updateRequest(Request request) {
        requestHandler.updateRequest(request);
    }

    public PayBike getPayBike() {
        return payBike;
    }

    public void newRentableNoID(String type, String name, double price, Position position,
                                boolean available, Uri imageLink,
                                String description) {
        updateModelRentables();
        // TODO change owner argument to respons from userHandler.getCurrentUser().getId()
        Rentable newRentable = RentableFactory.createRentableNoID(type, name, price,
                position, available, PayBike.getCurrentUser().getUserID(),
                imageLink, description);
        payBike.getModelRentables().add(newRentable);
        rentableHandler.addRentable(newRentable);

    }

    public boolean createUser(String email, String password, String name) {

        Task<AuthResult> signUp = userHandler.createUser(email, password, name);

        signUp.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // If sign up is successful
                String userID = userHandler.getCurrentUser().getUid();
                User newUser = new User(email, password, name, userID);
                PayBike.addModelUser(newUser);
                Log.d(TAG, "Local user (" + name + ") added!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // If sign up fails
                // Maybe do something that shows an error message on the phone
            }
        });

        // TODO: Now always returns true, should return false if sign up fails!
        return true;
    }

    public void updateCurrentUser() {
        PayBike.setCurrentUser(userHandler.convertGetCurrentUser());
    }

    public void signOut() {
        userHandler.signOut();
        updateCurrentUser();
    }

    public void createNewRequest(Rentable requested, LocalDateTime fromDateTime, LocalDateTime toDateTime, double price) {
        updateModelRequests();
        Request request = new Request(PayBike.getCurrentUser().getUserID(), requested.getId(), fromDateTime, toDateTime, price, Request.Answer.UNANSWERED);
        getModelRequests().add(request);
        requestHandler.add(request);

    }

    public User getCurrentUser() {
        return PayBike.getCurrentUser();
    }

}
