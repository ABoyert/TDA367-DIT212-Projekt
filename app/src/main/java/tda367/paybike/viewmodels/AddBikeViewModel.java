package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import tda367.paybike.handlers.BikeHandler;
import tda367.paybike.model.Bike;

public class AddBikeViewModel extends ViewModel {

    private BikeHandler bikeHandler;

    public AddBikeViewModel() {
        bikeHandler = new BikeHandler();
    }

    public void postBike(Bike bike) {
        bikeHandler.addBike(bike);
    }

}
