package tda367.paybike.Controller;

import java.util.List;

import tda367.paybike.database.DatabaseController;
import tda367.paybike.handlers.RentableHandler;
import tda367.paybike.handlers.RequestHandler;
import tda367.paybike.model.Bike;
import tda367.paybike.model.Model;
import tda367.paybike.model.Rentable;
import tda367.paybike.viewmodels.AddBikeViewModel;
import tda367.paybike.viewmodels.BikeViewModel;

public class Controller {

    /*
    *
    * Communicate data, not hold
    *
    * */
    private Model model;
    private RequestHandler requestHandler = new RequestHandler();
    private RentableHandler rentableHandler = new RentableHandler();

    //TODO Controller shouldn't be dependent on ViewModels...
    //private BikeViewModel feedViewModel;
    //private AddBikeViewModel addViewModel;

    public Controller() {
        //this.model = model;
       // this.feedViewModel = feedViewModel;
        //this.addViewModel = addViewModel;
    }



    public List<Rentable> getDatabaseRentables(){
        return rentableHandler.getAllRentables();
    }

    public void placeRentablesInModel(){
        model.setModelRentables(getDatabaseRentables());
    }

    public List<Rentable> getModelRentables(){
        return model.getModelRentables();
    }

    //TODO Controller shouldn't be dependent on ViewModels...
    /*
    public void giveFeedModelList(){
        feedViewModel.setAvailableRentables(getModelRentables());
    }

    public void giveAddModelList(){
        feedViewModel.setAvailableRentables(getModelRentables());
    }*/



}
