package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import tda367.paybike.R;
import tda367.paybike.adapters.CustomBikeAdAdapter;
import tda367.paybike.repository.Repository;
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
    private Repository r;

    private LocalDate fromDate, toDate;
    private LocalTime fromTime, toTime;

    public BikeViewModel() {
        r = new Repository();
    }

    public void setFromDate(LocalDate date) {
        fromDate = date;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setToDate(LocalDate date) {
        toDate = date;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setFromTime(LocalTime time) {
        fromTime = time;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setToTime(LocalTime time) {
        toTime = time;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public int getTotalPrice() {
        if (fromDate != null
                && fromTime != null
                &&  toDate != null
                && toTime != null) {
            LocalDateTime from = LocalDateTime.of(fromDate, fromTime);
            LocalDateTime to = LocalDateTime.of(toDate, toTime);

        }
        return 0;
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
        return r.updateAndGetRentables().stream()
                .filter(bike -> bike.isAvailable())
                .collect(toCollection(ArrayList::new));
    }

    public void updateViewModelRentables(){
        setAvailableRentables(getModelRentables());
    }

    public ArrayList<Rentable> getSearchResult(String searchText) {
        return getAvailableRentables().stream()
                .filter(bike -> bike.getName().toLowerCase().contains(searchText.toLowerCase())
                        || bike.getPosition().toLowerCase().contains(searchText.toLowerCase()))
                .collect(toCollection(ArrayList::new));
    }

    public Repository getRepository() {
        return r;
    }
}
