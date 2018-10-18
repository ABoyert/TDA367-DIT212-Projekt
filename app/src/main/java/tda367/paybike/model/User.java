package tda367.paybike.model;

import android.support.annotation.NonNull;

import java.util.Objects;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 *
 * App user variables and methods. Implemented as immutable object since user properties cannot be updated.
 *
 */

final public class User {

    @NonNull
    private String email, password, name;
    final private String userID;

    private void checkEmail(String email) throws IllegalArgumentException {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    private void checkPassword(String password) throws IllegalArgumentException {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password needs to be at least 8 characters long");
        }
    }

    public User(String email, String password, String name, String userID) {
        checkEmail(email);
        checkPassword(password);
        this.name = name;
        this.email = email;
        this.password = password;
        this.userID = userID;
    }

    public User(String email, String name, String userID){
        checkEmail(email);
        this.name = name;
        this.email = email;
        this.userID = userID;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getUserID() {
        return userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(userID, user.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name, userID);
    }

    @Override
    public String toString() {
        return "User Id: " + userID + "\nName: " + name + "\nEmail: " + email + "\nPassword: " + password;
    }
}
