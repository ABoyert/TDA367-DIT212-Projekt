package tda367.paybike.viewmodels;

import android.arch.lifecycle.ViewModel;
import tda367.paybike.Repository.Repository;

public class MainViewModel extends ViewModel {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MIN_NAME_LENGTH = 0;

    private String name, email, password, repeatedPassword;

    Repository c;

    public MainViewModel() {
        c = new Repository();
        name = "";
        email = "";
        password = "";
        repeatedPassword = "";
    }

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

    public boolean nameIsValid() {
        return getName().length() > MIN_NAME_LENGTH && getName().length() < MAX_NAME_LENGTH;
    }

    public boolean emailIsValid() {
        return email.contains("@");
    }

    public boolean passwordIsValid() {
        return getPassword().length() >= MIN_PASSWORD_LENGTH;
    }

    public boolean inputIsValid() {
        return emailIsValid() && passwordIsValid() && nameIsValid() &&passwordsMatch();
    }

    public boolean passwordsMatch() {
        return password.equals(repeatedPassword);
    }

    public boolean createUser() {
        return c.createUser(getEmail(), getPassword(), getName()) ? true : false;
    }

}
