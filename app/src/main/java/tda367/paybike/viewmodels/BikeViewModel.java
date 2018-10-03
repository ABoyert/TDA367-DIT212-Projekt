package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.Controller.Controller;
import tda367.paybike.handlers.RentableHandler;
import tda367.paybike.model.Bike;
import tda367.paybike.model.Rentable;

import static java.util.stream.Collectors.*;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class provides the BikeFeedActivity with data
 */

public class BikeViewModel extends ViewModel {

    private static RentableHandler rentableHandler;
    private List<Rentable> availableRentables;
    private Rentable selected;
    private Controller c;

    public BikeViewModel() {
        rentableHandler = new RentableHandler();
        c = new Controller();

    }

    public void setAvailableRentables(List<Rentable> availableRentables) {
        this.availableRentables = availableRentables;
    }

    //public void readAvailableRentables()

    public void select(Rentable selected) {
        this.selected = selected;
    }

    public Rentable getSelected() {
        return selected;
    }

    // Fetches and returns all Bike Objects from the Database which are marked as "available"

    public List<Rentable> getAvailableRentables() {
        return availableRentables.stream()
                .filter(bike -> bike.isAvailable())
                .collect(toCollection(ArrayList::new));
    }


    /*public ArrayList<Rentable> getAvailableRentables() {
        return rentableHandler.getAllRentables().stream()
                .filter(bike -> bike.isAvailable())
                .collect(toCollection(ArrayList::new));
    }*/



    //TODO Update method to filter correct attributes


    public ArrayList<Rentable> getSearchResult(String searchText) {
        return rentableHandler.getAllRentables().stream()
                .filter(bike -> bike.getId().toLowerCase().contains(searchText.toLowerCase()) || bike.getPosition().toLowerCase().contains(searchText.toLowerCase()))
                .collect(toCollection(ArrayList::new));
    }

}
