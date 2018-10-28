package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.model.Rentable;
import tda367.paybike.repository.Repository;

import static java.util.stream.Collectors.toCollection;

/* Created by Julia Gustafsson
 *
 * This ViewModel is responsible for handling the data which will be presented in the MyRentablesActivity.
 *
 * Important: This class should never hold a reference to an Activity or Fragment, nor it's context.
 */
public class MyRentablesViewModel extends ViewModel {

    /* Resources */
    private Repository r;
    private Rentable selected;

    /* Simple constructor */
    public MyRentablesViewModel() {
        r = new Repository();
    }

    /* Getters and setters */
    public String getUserName() {
        return r.getCurrentUser().getName();
    }

    public void setSelected(Rentable selected) {
        this.selected = selected;
    }

    public Rentable getSelected() {
        return selected;
    }

    /* Handles deletions of rentables */
    public void deleteSelectedRentable() {
        r.deleteRentable(selected);
    }

    /* Fetches the rentables which are owned by the current user to display them in MyRentablesActivity*/
    public List<Rentable> getCurrentUserRentables() {
        return r.updateAndGetRentables().stream()
                .filter(u -> u.getOwner().equals(r.getCurrentUser().getUserID()))
                .collect(toCollection(ArrayList::new));
    }

    /* Changes the availability of the selected rentable */
    public void toggleSelectedAvailability() {
        selected.setAvailable(!selected.isAvailable());
        r.updateRentable(selected);
    }

    /* Handles sign out */
    public void signOut() {
        r.signOut();
    }

}
