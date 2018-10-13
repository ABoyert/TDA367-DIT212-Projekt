package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.net.Uri;

import tda367.paybike.repository.Repository;

public class AddBikeViewModel extends ViewModel {

    // Constants
    private static final int MAX_PRICE = 1000;
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    private static final int MAX_TITLE_LENGTH = 50;

    private String bikeName, bikeDescription, bikePosition, bikePrice;
    private Uri bikeImagePath;
    private Repository r;

    public AddBikeViewModel() {
        r = new Repository();
        bikeName = "";
        bikeDescription = "";
        bikePosition = "";
        bikePrice = "";
        bikeImagePath = null;
    }

    // GETTERS AND SETTERS
    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getBikeDescription() {
        return bikeDescription;
    }

    public void setBikeDescription(String bikeDescription) {
        this.bikeDescription = bikeDescription;
    }

    public String getBikePosition() {
        return bikePosition;
    }

    public void setBikePosition(String bikePosition) {
        this.bikePosition = bikePosition;
    }

    public String getBikePrice() {
        return bikePrice;
    }

    public void setBikePrice(String bikePrice) {
        this.bikePrice = bikePrice;
    }

    public Uri getBikeImagePath() {
        return bikeImagePath;
    }

    public void setBikeImagePath(Uri bikeImagePath) {
        this.bikeImagePath = bikeImagePath;
    }

    // Checks if provided name/title is valid
    public boolean nameIsValid() {
        return bikeName.length() > 0 && bikeName.length() < MAX_TITLE_LENGTH;
    }

    public boolean positionIsValid() {
        //TODO Write method to check this
        return true;
    }

    // Checks if provided price is valid
    public boolean priceIsValid() {
        return bikePrice.length() > 0 && Double.parseDouble(bikePrice) < MAX_PRICE;
    }

    // Checks if provided description is valid
    public boolean descriptionIsValid() {
        return bikeDescription.length() > 0 && bikeDescription.length() < MAX_DESCRIPTION_LENGTH;
    }

    public boolean imageIsSelected() {
        return getBikeImagePath() != null;
    }

    public boolean inputIsValid() {
        return nameIsValid() &&
                descriptionIsValid() &&
                priceIsValid() &&
                positionIsValid() &&
                imageIsSelected();
    }

    // TODO Complete method once we have the image feature and users
    public void postBike() {

        r.newRentableNoID(false,"Bike", bikeName, Double.parseDouble(bikePrice),
                bikePosition, true, bikeImagePath, bikeDescription);
    }

    public void clearAll() {
        bikeName = "";
        bikeDescription = "";
        bikePosition = "";
        bikePrice = "";
        bikeImagePath = null;
    }

}

