package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.net.Uri;

import tda367.paybike.model.Position;
import tda367.paybike.repository.Repository;

/*
 * Created by Julia Gustafsson
 *
 * This ViewModel is responsible for handling the data which will be presented in the AddBikeActivity.
 *
 * Important: This class should never hold a reference to an Activity or Fragment, nor it's context.
 */

public class AddBikeViewModel extends ViewModel {

    /* Constants */
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    private static final int MAX_TITLE_LENGTH = 50;
    private static final double MIN_BIKE_PRICE = 0.01;
    private static final int MAX_CITY_LENGTH = 30;
    private static final int MAX_STREET_LENGTH = 50;
    private static final int ZIPCODE_LENGTH = 5;

    /* Resources */
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

    /* Getters and setters */
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

    /* Checks if provided name is valid */
    public boolean nameIsValid() {
        return bikeName.length() > 0 && bikeName.length() < MAX_TITLE_LENGTH;
    }

    /* Checks if provided street is valid. Cannot contain any commas as this is used for parsing Positions
     * from a String and commas may cause confusion. */
    public boolean streetIsValid() {
        return street.length() > 0 && street.length() < MAX_STREET_LENGTH && street.indexOf(',') == -1;
    }

    /* Checks if provided zipcode is valid */
    public boolean zipcodeIsValid() {
        return zipcode.length() == ZIPCODE_LENGTH;
    }

    /* Checks if provided city is valid. Cannot contain any commas as this is used for parsing Positions
    * from a String and commas may cause confusion. */
    public boolean cityIsValid() {
        return city.length() > 0 && city.length() < MAX_CITY_LENGTH && city.indexOf(',') == -1;
    }

    /* Checks if provided price is valid */
    public boolean priceIsValid() {
        return Double.parseDouble(bikePrice) >= MIN_BIKE_PRICE;
    }

    /* Checks if provided description is valid */
    public boolean descriptionIsValid() {
        return bikeDescription.length() > 0 && bikeDescription.length() < MAX_DESCRIPTION_LENGTH;
    }

    /* Checks if user has selected an image of the rentable */
    public boolean imageIsSelected() {
        return getBikeImagePath() != null;
    }

    /* Validates all the conditions above */
    public boolean inputIsValid() {
        return nameIsValid() &&
                descriptionIsValid() &&
                priceIsValid() &&
                streetIsValid() &&
                zipcodeIsValid() &&
                cityIsValid() &&
                imageIsSelected();
    }

    /* Adds the rentable to the database and the model */
    public void postRentable() {
        r.newRentableNoID("Bike", bikeName, Double.parseDouble(bikePrice),
                new Position(street, Integer.parseInt(zipcode), city), true, bikeImagePath, bikeDescription);
    }

    /* Clear all input fields */
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

