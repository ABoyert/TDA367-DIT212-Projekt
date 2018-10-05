package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

import tda367.paybike.handlers.RentableHandler;
import tda367.paybike.model.Bike;

import static java.util.stream.Collectors.*;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class provides the BikeFeedActivity with data
 */

public class BikeViewModel extends ViewModel {

    private static RentableHandler rentableHandler;
    private ArrayList<Bike> availableBikes;
    private Bike selected;

    public BikeViewModel() {
        rentableHandler = new RentableHandler();
    }

    public void setAvailableBikes(ArrayList<Bike> availableBikes) {
        this.availableBikes = availableBikes;
    }

    public void select(Bike selected) {
        this.selected = selected;
    }

    public Bike getSelected() {
        return selected;
    }

    // Fetches and returns all Bike Objects from the Database which are marked as "available"
    public ArrayList<Bike> getAvailableBikes() {
        return rentableHandler.getAllBikes().stream()
                .filter(bike -> bike.isAvailable())
                .collect(toCollection(ArrayList::new));
    }

    //TODO Update method to filter correct attributes
    public ArrayList<Bike> getSearchResult(String searchText) {
        return rentableHandler.getAllBikes().stream()
                .filter(bike -> bike.getName().toLowerCase().contains(searchText.toLowerCase()) || bike.getPosition().toLowerCase().contains(searchText.toLowerCase()))
                .collect(toCollection(ArrayList::new));
    }

}
