package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import tda367.paybike.repository.Repository;
import tda367.paybike.model.Rentable;

import static java.util.stream.Collectors.*;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This ViewModel is responsible for handling the data which will be presented in the RentableFeedActivity as
 * well as the RentableDetailsFragment and SendRequestFragment.
 *
 * Important: This class should never hold a reference to an Activity or Fragment, nor it's context.
 */

public class RentableViewModel extends ViewModel {

    /* Used for logging */
    private static final String TAG = "RentableFeedActivity";

    /* Resources */
    private List<Rentable> availableRentables;
    private Rentable selected;
    private Repository r;
    private LocalDateTime fromDateTime, toDateTime;

    /* The constructor fetches the current time which will be used in SendRequestFragment */
    public RentableViewModel() {
        r = new Repository();
        initTimes();
    }

    /* Getters and setters */
    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    public void setFromDate(LocalDate date) {
        LocalTime time = fromDateTime.toLocalTime();
        fromDateTime = LocalDateTime.of(date, time);
    }

    public LocalDate getFromDate() {
        return fromDateTime.toLocalDate();
    }

    public void setToDate(LocalDate date) {
        LocalTime time = toDateTime.toLocalTime();
        toDateTime = LocalDateTime.of(date, time);
    }

    public LocalDate getToDate() {
        return toDateTime.toLocalDate();
    }

    public void setFromTime(LocalTime time) {
        LocalDate date = fromDateTime.toLocalDate();
        fromDateTime = LocalDateTime.of(date, time);
    }

    public LocalTime getFromTime() {
        return fromDateTime.toLocalTime();
    }

    public void setToTime(LocalTime time) {
        LocalDate date = toDateTime.toLocalDate();
        toDateTime = LocalDateTime.of(date, time);
    }

    public LocalTime getToTime() {
        return toDateTime.toLocalTime();
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

    /* Calculates the price of for a new request based on the submitted times. Price 0 is returned
    * if the times are invalid. It works as an indicator that the price could not be calculated. */
    public double getTotalPrice() {
        double price = 0;
        if (timesAreValid()) {
            long minBetween = ChronoUnit.MINUTES.between(fromDateTime, toDateTime);
            int hours = (int) minBetween / 60;
            int minutes = (int) (minBetween % 60);
            double rate = selected.getPrice();
            /* Lowest price is for one hour, if more than one hour, calculates exact price */
            price = hours < 1 ? selected.getPrice() : (hours * rate) + (minutes * rate / 60);
        }
        return price;
    }

    /* Validates the condition that the from date needs to be before the to date */
    public boolean fromDateIsBeforeToDate() {
        return fromDateTime.isBefore(toDateTime);
    }

    /* Validates the condition that the from date is in the future */
    public boolean fromDateIsAfterNow() {
        return LocalDateTime.now().isBefore(fromDateTime);
    }

    /* Validates the condition that the from date is not within the next hour */
    public boolean toDateIsOneHourAfterFromDate() {
        if (fromDateIsBeforeToDate()) {
            long min = ChronoUnit.MINUTES.between(fromDateTime, toDateTime);
            return min >= 60;
        } else {
            return false;
        }
    }

    /* Validates all the conditions above */
    public boolean timesAreValid() {
        return fromDateIsBeforeToDate()
                && fromDateIsAfterNow()
                && toDateIsOneHourAfterFromDate();
    }

    /* Validates all the conditions above */
    public void initTimes() {
        fromDateTime = LocalDateTime.now().withMinute(00).plusHours(2);
        toDateTime = LocalDateTime.now().withMinute(00).plusHours(3);
    }

    /* Creates a new request with the given times and the calculated price */
    public void createNewRequest() {
        if (timesAreValid()) {
            try {
                r.createNewRequest(selected, fromDateTime, toDateTime, getTotalPrice());
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "Request could not be created, illegal arguments. " + "From: " + fromDateTime.toString() + " To: " + toDateTime.toString());
            }
        }
    }

    /* Checks if the current user owns a selected rentable */
    public boolean isUserRentableOwner() {
        return r.getCurrentUser().getUserID().equals(selected.getOwner());
    }

    /* Fetches and returns all Bike Objects from the PayBike which are marked as "available" */
    public List<Rentable> getModelRentables() {
        return r.updateAndGetRentables().stream()
                .filter(bike -> bike.isAvailable())
                .collect(toCollection(ArrayList::new));
    }

    /* Fetches all rentables from the database and updates the model to enable updates */
    public void updateViewModelRentables() {
        setAvailableRentables(getModelRentables());
    }

    /* Returns a List of Rentables which matches the given String */
    public ArrayList<Rentable> getSearchResult(String searchText) {
        return getAvailableRentables().stream()
                .filter(bike -> bike.getName().toLowerCase().contains(searchText.toLowerCase())
                        || bike.getPosition().getCity().toLowerCase().contains(searchText.toLowerCase()))
                .collect(toCollection(ArrayList::new));
    }

    /* Handles sign out */
    public void signOut() {
        r.signOut();
    }
}
