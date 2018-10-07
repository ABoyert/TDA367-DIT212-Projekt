package tda367.paybike.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Orava Kilberg on 2018-10-02.
 */

public class Model {

    /*
    Holds data, reflects database.
     */

    private List<Rentable> modelRentables = new ArrayList<>();
    private List<User> modelUsers = new ArrayList<>();
    private List<Request> modelRequests = new ArrayList<>();
    private User currentUser;

    public Model() {

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

    public void addModelUser(User newUser) {
        modelUsers.add(newUser);
    }

    public List<Request> getModelRequests() {
        return modelRequests;
    }

    public void setModelRequests(List<Request> modelRequests) {
        this.modelRequests = modelRequests;
    }


}
