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

    private static final String INVALID_USERNAME = "Username might be incorrect ";
    private static final String NO_USERNAME = "You have to enter your username ";
    private static final String INVALID_PASSWORD = "Password might be incorrect ";
    private static final String NO_PASSWORD = "You have to enter your password ";

    private String userName, passWord;

    public String getWarning(String userName,String userPassword) {
        String warningMessage = "";
            warningMessage += (userName.length() != 0) ? INVALID_USERNAME : NO_USERNAME;
            warningMessage += (userPassword.length() != 0) ? INVALID_PASSWORD : NO_PASSWORD;

        return warningMessage;
    }
}
