package tda367.paybike.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Orava Kilberg on 2018-10-02.
 */

public class PayBike {

    /*
    Holds data, reflects database.
     */

    private static List<Rentable> modelRentables = new ArrayList<>();
    private static List<User> modelUsers = new ArrayList<>();
    private static List<Request> modelRequests = new ArrayList<>();
    private static User currentUser;

    // Instance variable
    private static PayBike instance = null;

    private PayBike() {
        // Singleton
    }

    // Create singleton if it does not exist, otherwise return it.
    public static PayBike getInstance() {
        if (instance == null) {
            instance = new PayBike();
        }

        return instance;
    }

    public List<Rentable> getModelRentables() {
        return modelRentables;
    }

    public void setModelRentables(List<Rentable> modelRentables) {
        this.modelRentables = modelRentables;
    }

    public void addRentable(Rentable newRentable){
        getModelRentables().add(newRentable);
    }

    public List<User> getModelUsers() {
        return modelUsers;
    }

    public void setModelUsers(List<User> modelUsers) {
        this.modelUsers = modelUsers;
    }

    public static void addModelUser(User newUser) {
        modelUsers.add(newUser);
    }

    public List<Request> getModelRequests() {
        return modelRequests;
    }

    public void setModelRequests(List<Request> modelRequests) {
        this.modelRequests = modelRequests;
    }

    //TODO Request Adding/Removing/Confirming


}
