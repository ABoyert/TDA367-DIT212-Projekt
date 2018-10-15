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

    //TODO ERROR: Just nu kan inte användare från databasen laddas in då Users har lösenord :/ som inte går att hämta från Databasen :(

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

    public void addRequest(User sendingUser, Rentable targetRentable, LocalDateTime fromDateTime, LocalDateTime toDateTime){
        modelRequests.add(new Request(sendingUser.getUserID(), targetRentable.getId(), fromDateTime, toDateTime));
    }

    public Request getRequest(){


        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        PayBike.currentUser = currentUser;
    }

    //TODO Request Adding/Removing/Confirming

    /*public boolean checkLogin(String email, String password){
        User loginUser = emailExists(email);
        if(comparePassword(password, loginUser)){
            setCurrentUser(loginUser);
            return true;
        }
        return false;

    }*/

    public boolean userExists(User user){
        for (User existingUser :getModelUsers()
             ) {
            if (user.getUserID() == existingUser.getUserID()){
                return true;
            }
        }
        return false;
    }



    public boolean emailExists(String email){
        for (User existingUser: getModelUsers()
             ) {
            if(email == existingUser.getEmail()){
                return true;
            }
        }

        return false;
    }

    public User findAndReturnUser(String userID){
        for (User existingUser: getModelUsers()
             ) {
            if(userID == existingUser.getUserID()){
                return existingUser;
            }
        }
        return null;
    }

    public boolean comparePassword(String password, User user){

        if(password == user.getPassword()){
            return true;
        }
        return false;

    }

    public void updateCurrentUser(String id){
        setCurrentUser(findAndReturnUser(id));
    }




}
