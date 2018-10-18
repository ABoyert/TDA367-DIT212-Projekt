package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.net.Uri;

import tda367.paybike.model.Position;
import tda367.paybike.repository.Repository;

public class AddBikeViewModel extends ViewModel {

    // Constants
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    private static final int MAX_TITLE_LENGTH = 50;
    private static final int MAX_CITY_LENGTH = 30;
    private static final int MAX_STREET_LENGTH = 50;
    private static final int ZIPCODE_LENGTH = 5;

    private String bikeName,
            bikeDescription,
            bikePrice,
            street,
            zipcode,
            city;
    private Uri bikeImagePath;
    private Repository r;

    public AddBikeViewModel() {
        r = new Repository();
        clearAll();
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public boolean streetIsValid() {
        return street.length() > 0 && street.length() < MAX_STREET_LENGTH && street.indexOf(',') == -1;
    }
    public boolean zipcodeIsValid() {
        return zipcode.length() == ZIPCODE_LENGTH;
    }

    public boolean cityIsValid() {
        return city.length() > 0 && city.length() < MAX_CITY_LENGTH && city.indexOf(',') == -1;
    }

    // Checks if provided price is valid
    public boolean priceIsValid() {
        return bikePrice.length() > 0;
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
                streetIsValid() &&
                zipcodeIsValid() &&
                cityIsValid() &&
                imageIsSelected();
    }

    public void postBike() {
        r.newRentableNoID("Bike", bikeName, Double.parseDouble(bikePrice),
                new Position(street, Integer.parseInt(zipcode), city), true, bikeImagePath, bikeDescription);
    }

    public void clearAll() {
        bikeName = "";
        bikeDescription = "";
        bikePrice = "";
        street = "";
        zipcode = "";
        city = "";
        bikeImagePath = null;
    }
}

