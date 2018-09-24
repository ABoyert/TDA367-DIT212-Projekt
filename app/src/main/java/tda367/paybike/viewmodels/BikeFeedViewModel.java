package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tda367.paybike.handlers.BikeHandler;
import tda367.paybike.model.Bike;

import static java.util.stream.Collectors.*;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class provides the BikeFeedActivity with data
 */

public class BikeFeedViewModel extends ViewModel {

    private static BikeHandler bikeHandler;

    public BikeFeedViewModel() {
        bikeHandler = new BikeHandler();
    }

    // Fetches and returns all available Bike Objects from the Database
    public ArrayList<Bike> getAvailableBikes() {
        return bikeHandler.getAllBikes().stream()
                .filter(bike -> bike.isAvailable())
                .collect(toCollection(ArrayList::new));
    }

    public ArrayList<Bike> getSearchResult(String searchText) {
        return bikeHandler.getAllBikes().stream()
                .filter(bike -> bike.getId().contains(searchText))
                .collect(toCollection(ArrayList::new));
    }

}
