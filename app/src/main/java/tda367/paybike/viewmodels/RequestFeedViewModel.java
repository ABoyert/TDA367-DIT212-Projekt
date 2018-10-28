package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import tda367.paybike.model.Request;
import tda367.paybike.repository.Repository;

/*
 * Created by Julia Gustafsson.
 *
 * This ViewModel is responsible for handling the data which will be presented in the RequestFeedActivity.
 *
 * Important: This class should never hold a reference to an Activity or Fragment, nor it's context.
 */

public class RequestFeedViewModel extends ViewModel {

    /* Resources */
    private Repository r;
    private List<Request> requests;

    public RequestFeedViewModel() {
        r = new Repository();
        requests = r.getCurrentUserRequests();
    }

    /* Getters and setters */
    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    /* Fetches all requests from the database to enable updates */
    public void updateRequests() {
        requests = r.getCurrentUserRequests();
    }

    /* Handles sign out  */
    public void signOut() {
        r.signOut();
    }
}
