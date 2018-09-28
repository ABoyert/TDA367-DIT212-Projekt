package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tda367.paybike.activities.BikeDetailsActivity;
import tda367.paybike.activities.BikeFeedActivity;
import tda367.paybike.handlers.BikeHandler;
import tda367.paybike.model.Bike;

import static java.util.stream.Collectors.*;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class provides the BikeFeedActivity with data
 */

public class BikeViewModel extends ViewModel {

    private static BikeHandler bikeHandler;
    private ArrayList<Bike> availableBikes;
    private Bike selected;

    public BikeViewModel() {
        bikeHandler = new BikeHandler();
    }

    public void setAvaiableBikes(ArrayList<Bike> availableBikes) {
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
        return bikeHandler.getAllBikes().stream()
                .filter(bike -> bike.isAvailable())
                .collect(toCollection(ArrayList::new));
    }

    //TODO Update method to filter correct attributes
    public ArrayList<Bike> getSearchResult(String searchText) {
        return bikeHandler.getAllBikes().stream()
                .filter(bike -> bike.getId().toLowerCase().contains(searchText.toLowerCase()) || bike.getPosition().toLowerCase().contains(searchText.toLowerCase()))
                .collect(toCollection(ArrayList::new));
    }

}
