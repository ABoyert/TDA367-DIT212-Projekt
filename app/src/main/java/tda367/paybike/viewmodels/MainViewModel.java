package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.handlers.RentableHandler;
import tda367.paybike.model.Bike;
import tda367.paybike.model.Rentable;
import tda367.paybike.model.User;

public class MainViewModel extends ViewModel{

    private User currentUser;
    private static RentableHandler rentableHandler;

    private static final String INVALID_USERNAME = "Username is incorrect";
    private static final String NO_USERNAME = "You have to enter your username";
    private static final String INVALID_PASSWORD = "Password is incorrect";
    private static final String NO_PASSWORD = "You have to enter your password";

    private String userName, passWord;


    /*
    // First checks if provided userName is valid, then if passWord match.
    public boolean userIsValid(String userName) {
        List<User> bikeList = rentableHandler.getAllBikes();
        int i = 0;
        Bike bike = bikeList.get(i);
        while (bike.getOwner().get != userName){
            if (bike.getName().equals(userName)){
                passWordIsValid();
            }
        }
        if (true){
            passWordIsValid();
        }
        return true;
    }

    public boolean passWordIsValid(User user) {
        return true;
    }

    public String getWarning() {
        String warningMessage = "";
        if (!userIsValid()) {
            warningMessage += (userName != "") ? INVALID_USERNAME : NO_USERNAME;
        }
        if (!passWordIsValid()) {
            warningMessage += (passWord != "") ? INVALID_PASSWORD : NO_PASSWORD;
        }
        return warningMessage;
    }
    */

    //göra private controller

    //metod som kollar om namet är valid typ
}
