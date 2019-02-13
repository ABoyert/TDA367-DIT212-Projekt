package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;

import tda367.paybike.repositori.Repository;

/*
 * Created by Julia Gustafsson and Anton Boyert
 *
 * This ViewModel is responsible for handling the data which will be presented in the LoginActivity as well
 * as the RegisterUserFragment.
 *
 * Important: This class should never hold a reference to an Activity or Fragment, nor it's context.
 */

public class LoginViewModel extends ViewModel {

    /* Constants*/
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MIN_NAME_LENGTH = 0;
    private static final String INVALID_USERNAME = "Username might be incorrect ";
    private static final String NO_USERNAME = "You have to enter your username.";
    private static final String INVALID_PASSWORD = "Password might be incorrect ";
    private static final String NO_PASSWORD = "You have to enter your password. ";

    /* Resources */
    private String name, email, password, repeatedPassword;
    Repository r;

    public LoginViewModel() {
        r = new Repository();
        name = "";
        email = "";
        password = "";
        repeatedPassword = "";
    }

    /* Getters and setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    /* Checks if given name is valid */
    public boolean nameIsValid() {
        return getName().length() > MIN_NAME_LENGTH && getName().length() < MAX_NAME_LENGTH;
    }

    /* Checks if given email is valid */
    public boolean emailIsValid() {
        return email.contains("@");
    }

    /* Checks if password name is valid */
    public boolean passwordIsValid() {
        return getPassword().length() >= MIN_PASSWORD_LENGTH;
    }

    /* Validates all conditions above */
    public boolean inputIsValid() {
        return emailIsValid() && passwordIsValid() && nameIsValid() && passwordsMatch();
    }

    /* Checks if both password fields match */
    public boolean passwordsMatch() {
        return password.equals(repeatedPassword);
    }

    /* Creates a new user */
    public boolean createUser() {
        return r.createUser(getEmail(), getPassword(), getName());
    }

    /* Updates the current user */
    public void updateCurrentUser() {
        r.updateCurrentUser();
    }

    /* Generate a warning message to inform user about what is wrong with the provided input */
    public String getWarning(String userName, String userPassword) {
        String warningMessage = "";
        warningMessage += (userName.length() != 0) ? INVALID_USERNAME : NO_USERNAME;
        warningMessage += (userPassword.length() != 0) ? INVALID_PASSWORD : NO_PASSWORD;
        return warningMessage;
    }

}
