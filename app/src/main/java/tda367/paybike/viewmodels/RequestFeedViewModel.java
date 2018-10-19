package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import tda367.paybike.model.Request;
import tda367.paybike.repository.Repository;

public class RequestFeedViewModel extends ViewModel {

    private Repository r;

    private List<Request> requests;

    public RequestFeedViewModel() {
        r = new Repository();
        requests = r.getCurrentUserRequests();
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public void updateRequests() {
        requests = r.getCurrentUserRequests();
    }
}
