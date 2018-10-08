package tda367.paybike.Repository;

import android.net.Uri;

import java.util.List;

import tda367.paybike.handlers.RentableHandler;
import tda367.paybike.handlers.RequestHandler;
import tda367.paybike.handlers.UserHandler;
import tda367.paybike.model.PayBike;
import tda367.paybike.model.Rentable;
import tda367.paybike.model.RentableFactory;
import tda367.paybike.model.User;

public class Repository {

    /*
    *
    * Passes data
    *
    * */
    private PayBike payBike = new PayBike();
    private RequestHandler requestHandler = RequestHandler.getInstance();
    private RentableHandler rentableHandler = RentableHandler.getInstance();
    private UserHandler userHandler = UserHandler.getInstance();

    // Set TAG to class name for use in debugging
    private static final String TAG = Controller.class.getSimpleName();

    public Repository() {

    }

    //Retrieves rentables from database
    public List<Rentable> getDatabaseRentables(){
        return rentableHandler.getAllRentables();
    }

    //Puts rentables from database in payBike
    public void updateModelRentables(){
        payBike.setModelRentables(getDatabaseRentables());
    }

    //Get list of rentables from payBike
    public List<Rentable> getModelRentables(){
        return payBike.getModelRentables();
    }

    //Updates the payBike with rentables from the database and returns the updated list
    public List<Rentable>updateAndGetRentables(){
        updateModelRentables();
        return getModelRentables();
    }

    public PayBike getPayBike(){
        return payBike;
    }

    public void newRentable(boolean withID, String type, String name, double price, String position,
                                boolean available, String owner, Uri imageLink,
                                String description, String id){

        Rentable newRentable = RentableFactory.createRentable(type, name, price,
                                                                position, available, owner,
                                                                    imageLink, description, id);

        rentableHandler.addRentable(newRentable);
        updateModelRentables();

    }

    public void newRentableNoID(boolean withID, String type, String name, double price, String position,
                            boolean available, String owner, Uri imageLink,
                            String description){

        Rentable newRentable = RentableFactory.createRentableNoID(type, name, price,
                position, available, owner,
                imageLink, description);

        rentableHandler.addRentable(newRentable);
        updateModelRentables();
    }

    public boolean createUser(String email, String password, String name) {
        Task<AuthResult> signUp = userHandler.createUser(email, password, name);

        signUp.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // If sign up is successful
                User newUser = new User(email, password, name);
                model.addModelUser(newUser);
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
}
