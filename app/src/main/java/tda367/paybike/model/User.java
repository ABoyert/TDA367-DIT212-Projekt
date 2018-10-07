package tda367.paybike.model;

import android.support.annotation.NonNull;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 *
 * App user variables and methods. Implemented as immutable object since user properties cannot be updated.
 *
 */

final public class User {

    @NonNull
    final private String email, password, name;

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

    public User(String email, String password, String name) {
        checkEmail(email);
        checkPassword(password);
        this.name = name;
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
    public String getName() {
        return name;
    }

}
