package tda367.paybike.viewmodels;

/*
* This class controls the BikeFeedActivity
*/

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tda367.paybike.handlers.BikeHandler;
import tda367.paybike.model.Bike;

public class BikeFeedViewModel extends ViewModel {

    private static BikeHandler bikeHandler;

    public BikeFeedViewModel() {
        bikeHandler = new BikeHandler();
    }

    // Fetches and returns all available Bike Objects from the Database
    public ArrayList<Bike> getAvailableBikes() {

        return bikeHandler.getAllBikes().stream()
                .filter(bike -> bike.isAvailable())
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
