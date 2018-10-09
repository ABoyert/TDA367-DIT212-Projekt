package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.Repository.Repository;
import tda367.paybike.model.Rentable;

import static java.util.stream.Collectors.*;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class provides the BikeFeedActivity with data
 */

public class BikeViewModel extends ViewModel {

    private List<Rentable> availableRentables;
    private Rentable selected;
    private Repository c;

    public BikeViewModel() {
        c = new Repository();
    }

    public void setAvailableRentables(List<Rentable> availableRentables) {
        this.availableRentables = availableRentables;
    }

    public List<Rentable> getAvailableRentables() {
        updateViewModelRentables();
        return availableRentables;
    }

    public void select(Rentable selected) {
        this.selected = selected;
    }

    public Rentable getSelected() {
        return selected;
    }

    // Fetches and returns all Bike Objects from the PayBike which are marked as "available"

    public List<Rentable> getModelRentables() {
        return c.updateAndGetRentables().stream()
                .filter(bike -> bike.isAvailable())
                .collect(toCollection(ArrayList::new));
    }

    public void updateViewModelRentables(){
        setAvailableRentables(getModelRentables());
    }

    //TODO Update method to filter correct attributes

    public ArrayList<Rentable> getSearchResult(String searchText) {
        return getAvailableRentables().stream()
                .filter(bike -> bike.getName().toLowerCase().contains(searchText.toLowerCase()) || bike.getPosition().toLowerCase().contains(searchText.toLowerCase()))
                .collect(toCollection(ArrayList::new));
    }

}
