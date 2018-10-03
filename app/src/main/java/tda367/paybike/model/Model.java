package tda367.paybike.model;

import java.util.List;

/**
 * Created by Oscar Orava Kilberg on 2018-10-02.
 */

public class Model {

    /*
    Hold data, not communicate
     */

    private List<Rentable> modelRentables;
    private List<User> modelUsers;
    private List<Request> modelRequests;
    private User currentUser;

    public Model(List<Rentable> modelRentables, List<User> modelUsers, List<Request> modelRequests) {
        this.modelRentables = modelRentables;
        this.modelUsers = modelUsers;
        this.modelRequests = modelRequests;
    }

    public List<Rentable> getModelRentables() {
        return modelRentables;
    }

    public void setModelRentables(List<Rentable> modelRentables) {
        this.modelRentables = modelRentables;
    }

    public List<User> getModelUsers() {
        return modelUsers;
    }

    public void setModelUsers(List<User> modelUsers) {
        this.modelUsers = modelUsers;
    }

    public List<Request> getModelRequests() {
        return modelRequests;
    }

    public void setModelRequests(List<Request> modelRequests) {
        this.modelRequests = modelRequests;
    }
}
