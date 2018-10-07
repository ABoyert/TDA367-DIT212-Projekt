package tda367.paybike.model;

import android.support.annotation.NonNull;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 *
 * App user variables and methods. Implemented as immutable object since user properties cannot be updated.
 *
 */

public class User {

    @NonNull
    private final String email, password, firstName, lastName;

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

    public User(String firstName, String lastName, String email, String password) {
        checkEmail(email);
        checkPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

}
