package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import tda367.paybike.activities.RentableFeedActivity;
import tda367.paybike.repository.Repository;
import tda367.paybike.model.Rentable;

import static java.util.stream.Collectors.*;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class provides the BikeFeedActivity with data
 */

public class RentableViewModel extends ViewModel {

    private static final String TAG = "RentableFeedActivity";

    private List<Rentable> availableRentables;
    private Rentable selected;
    private Repository r;

    private LocalDateTime fromDateTime, toDateTime;

    public RentableViewModel() {
        r = new Repository();
        fromDateTime = LocalDateTime.now();
        System.out.println("From Date: " + fromDateTime);
        toDateTime = LocalDateTime.now().plus(1,ChronoUnit.HOURS);
        System.out.println("To Date: " + toDateTime);
    }

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

    public double getTotalPrice() {
        double price = 0;
        if (getFromDate() != null
                && getFromTime() != null
                && getToDate() != null
                && getToTime() != null) {
            long minBetween = ChronoUnit.MINUTES.between(fromDateTime,toDateTime);
            int hours = (int) minBetween / 60;
            int minutes = (int) (minBetween % 60);
            double rate = selected.getPrice();
            // Lowest price is for one hour, if more than one hour, calculate exact price
            price = hours < 1 ? selected.getPrice() : (hours * rate) + (minutes * rate/60);
        }
        return price;
    }

    public boolean fromDateIsBeforeToDate() {
        return fromDateTime.isBefore(toDateTime);
    }

    public boolean fromDateIsAfterNow() {
        return LocalDateTime.now().isBefore(fromDateTime);
    }

    public boolean toDateIsOneHourAfterFromDate() {
        if (fromDateIsBeforeToDate()) {
            long min = ChronoUnit.MINUTES.between(fromDateTime,toDateTime);
            return min >= 60;
        } else {
            return false;
        }
    }

    public boolean timesAreValid() {
        return fromDateIsBeforeToDate()
                && fromDateIsAfterNow()
                && toDateIsOneHourAfterFromDate();
    }

    public void initTimes() {
        fromDateTime = LocalDateTime.now().withMinute(00).plusHours(2);
        toDateTime = LocalDateTime.now().withMinute(00).plusHours(3);
    }

    public void createNewRequest() {
        if (timesAreValid()) {
            try {
                r.createNewRequest(selected, fromDateTime, toDateTime, getTotalPrice());
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "Request could not be created, illegal arguments. " + "From: " + fromDateTime.toString() + " To: " + toDateTime.toString());
            }
        }
    }

    public boolean isUserRentableOwner() {
        if (r.getCurrentUser().getUserID().equals(selected.getOwner())) {
            return true;
        } else {
            return false;
        }
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
                        || bike.getPosition().getCity().toLowerCase().contains(searchText.toLowerCase()))
                .collect(toCollection(ArrayList::new));
    }

    public void signOut() {
        r.signOut();
    }

}
