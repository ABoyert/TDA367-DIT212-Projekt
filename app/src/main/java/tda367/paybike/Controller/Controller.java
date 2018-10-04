package tda367.paybike.Controller;

import java.util.List;

import tda367.paybike.database.DatabaseController;
import tda367.paybike.handlers.RentableHandler;
import tda367.paybike.handlers.RequestHandler;
import tda367.paybike.model.Bike;
import tda367.paybike.model.Model;
import tda367.paybike.model.Rentable;
import tda367.paybike.model.RentableFactory;
import tda367.paybike.viewmodels.AddBikeViewModel;
import tda367.paybike.viewmodels.BikeViewModel;

public class Controller {

    /*
    *
    * Passes data, not hold
    *
    * */
    private Model model = new Model();
    private RequestHandler requestHandler = new RequestHandler();
    private RentableHandler rentableHandler = new RentableHandler();



    public Controller() {

    }

    //Retrieves rentables from database
    public List<Rentable> getDatabaseRentables(){
        return rentableHandler.getAllRentables();
    }

    //Puts rentables from database in model
    public void updateModelRentables(){
        model.setModelRentables(getDatabaseRentables());
    }

    //Get list of rentables from model
    public List<Rentable> getModelRentables(){
        return model.getModelRentables();
    }

    //Updates the model with rentables from the database and returns the updated list
    public List<Rentable>updateAndGetRentables(){
        updateModelRentables();
        return getModelRentables();
    }

    public Model getModel(){
        return model;
    }

    public void newRentable(boolean withID, String type, String name, double price, String position,
                                boolean available, String owner, String imageLink,
                                String description, String id){

        Rentable newRentable = RentableFactory.createRentable(type, name, price,
                                                                position, available, owner,
                                                                    imageLink, description, id);


        rentableHandler.addRentable(newRentable);
        updateModelRentables();

    }

    public void newRentableNoID(boolean withID, String type, String name, double price, String position,
                            boolean available, String owner, String imageLink,
                            String description){

        Rentable newRentable = RentableFactory.createRentableNoID(type, name, price,
                position, available, owner,
                imageLink, description);

        rentableHandler.addRentable(newRentable);
        updateModelRentables();
    }





}
