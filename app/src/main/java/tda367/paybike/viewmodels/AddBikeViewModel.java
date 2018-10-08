package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import tda367.paybike.Controller.Controller;
import tda367.paybike.model.Position;

public class AddBikeViewModel extends ViewModel {

    // Constants
    private static final int MAX_PRICE = 1000;
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    private static final int MAX_TITLE_LENGTH = 50;

    private static final String INVALID_NAME = "Bike title cannot exceed " + MAX_TITLE_LENGTH + " characters. ";
    private static final String NO_NAME = "You have to set a title of the bike object. ";
    private static final String INVALID_POSITION = "Invalid address format. ";
    private static final String NO_POSITION = "You need to state where the bike is located so other users to find it. ";
    private static final String INVALID_DESCRIPTION = "Description cannot exceed " + MAX_DESCRIPTION_LENGTH + " characters. ";
    private static final String NO_DESCRIPTION = "Please tell us something about your bike in the description. ";
    private static final String INVALID_PRICE = "Price cannot exceed $" + MAX_PRICE + ". ";
    private static final String NO_PRICE = "You have to set at price. ";

    private String bikeName, bikeDescription, bikePrice, warningMessage;
    private Position bikePosition;
    private boolean warning;
    private Controller c;

    public AddBikeViewModel() {
        c = new Controller();
        warning = false;
        bikeName = "";
        bikeDescription = "";
        bikePosition = new Position("", 0, "", "");
        bikePrice = "";
        warningMessage = "";
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

    public Position getBikePosition() {
        return bikePosition;
    }

    public void setBikePosition(Position bikePosition) {
        this.bikePosition = bikePosition;
    }

    public String getBikePrice() {
        return bikePrice;
    }

    public void setBikePrice(String bikePrice) {
        this.bikePrice = bikePrice;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public boolean warningIsShown() {
        return warning;
    }

    public void showWarning(boolean status) {
        this.warning = status;
    }

    // Checks if provided name/title is valid
    private boolean nameIsValid() {
        return bikeName != "" && bikeName.length() < MAX_TITLE_LENGTH ? true : false;
    }

    private boolean positionIsValid() {
        //TODO Write method to check this
        return true;
    }

    // Checks if provided price is valid
    private boolean priceIsValid() {
        return bikePrice != "" && Double.parseDouble(bikePrice) < MAX_PRICE ? true : false;
    }

    // Checks if provided description is valid
    private boolean descriptionIsValid() {
        return bikePrice != "" && bikeDescription.length() < MAX_DESCRIPTION_LENGTH ? true : false;
    }

    public boolean inputIsValid() {
        return nameIsValid() &&
                descriptionIsValid() &&
                priceIsValid() &&
                positionIsValid();
    }

    // Method that generates warning messages when user input is incorrect
    public String getWarning() {
        String warningMessage = "";
            if (!nameIsValid()) {
                warningMessage += (bikeName != "") ? INVALID_NAME : NO_NAME;
            } /*
            if (!positionIsValid()) {
                warningMessage += (bikePosition != "") ? INVALID_POSITION : NO_POSITION;
            } */
            if (!descriptionIsValid()) {
                warningMessage += (bikeDescription != "") ? INVALID_DESCRIPTION : NO_DESCRIPTION;
            }
            if (!priceIsValid()) {
                warningMessage += (bikePrice != "") ? INVALID_PRICE : NO_PRICE;
            }

        return warningMessage;
    }

    // TODO Complete method once we have the image feature and users
    public void postBike() {

        c.newRentableNoID(false,"Bike", bikeName, Double.parseDouble(bikePrice),
                bikePosition, true, "owner",
                "imagelink", bikeDescription);
    }



}

