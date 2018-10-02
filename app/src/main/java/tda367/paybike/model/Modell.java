package tda367.paybike.model;

import java.util.List;

/**
 * Created by Oscar Orava Kilberg on 2018-10-02.
 */

public class Modell {

    private List<Rentable> modellRentables;
    private List<User> modellUsers;
    private List<Request> modellRequests;

    public Modell(List<Rentable> modellRentables, List<User> modellUsers, List<Request> modellRequests) {
        this.modellRentables = modellRentables;
        this.modellUsers = modellUsers;
        this.modellRequests = modellRequests;
    }

    public List<Rentable> getModellRentables() {
        return modellRentables;
    }

    public void setModellRentables(List<Rentable> modellRentables) {
        this.modellRentables = modellRentables;
    }

    public List<User> getModellUsers() {
        return modellUsers;
    }

    public void setModellUsers(List<User> modellUsers) {
        this.modellUsers = modellUsers;
    }

    public List<Request> getModellRequests() {
        return modellRequests;
    }

    public void setModellRequests(List<Request> modellRequests) {
        this.modellRequests = modellRequests;
    }
}
