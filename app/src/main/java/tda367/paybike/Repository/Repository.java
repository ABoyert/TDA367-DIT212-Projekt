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
        if (userHandler.createUser(email, password, name)) {
            User newUser = new User(email, password, name);
            payBike.addModelUser(newUser);
            return true;
        } else {
            return false;
        }
    }

    public void uploadRentableImage(Rentable rentable, Uri filePath) {

    }
}
