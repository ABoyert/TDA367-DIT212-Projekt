package tda367.paybike.viewmodels;

/*
* This class controls the BikeFeedActivity
*/

import android.arch.lifecycle.ViewModel;

import java.util.stream.Stream;

import tda367.paybike.handlers.BikeHandler;
import tda367.paybike.model.Bike;

public class BikeFeedViewModel extends ViewModel {

    private static BikeHandler bikeHandler;

    public BikeFeedViewModel() {
        bikeHandler = new BikeHandler();

    }

    // Fetches and returns all available Bike Objects from the Database
    public Stream<Bike> getAvailableBikes() {
        return bikeHandler.getAllBikes().stream()
                .filter(bike -> bike.isAvailable()); // Filters out and returns only available bikes
    }

}
