package tda367.paybike.model;

import java.time.LocalDateTime;
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

    //TODO Filtrering, Checks för lösenord, samma cyklar, likadana id, samma email osv.

    public Rentable getRentableFromId(String id) {
        for (Rentable r : modelRentables) {
            if (r.getId().equals(id)) {
                return r;
            }
        }

        return null;
    }

    public List<Rentable> getModelRentables() {
        return modelRentables;
    }



    public void setModelRentables(List<Rentable> modelRentables) {
        this.modelRentables = modelRentables;
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

    public void addRequest(User sendingUser, Rentable targetRentable, LocalDateTime fromDateTime, LocalDateTime toDateTime, double price){
        modelRequests.add(new Request(sendingUser.getUserID(), targetRentable.getId(), fromDateTime, toDateTime, price, Request.Answer.UNANSWERED));
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        PayBike.currentUser = currentUser;
    }

    //TODO Request Adding/Removing/Confirming

}
