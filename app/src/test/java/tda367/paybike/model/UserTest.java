package tda367.paybike.model;

import android.net.Uri;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test (expected = IllegalArgumentException.class)
    public void emailIsUnique() {
        User user1 = new User("gustafsson@live.se", "123", "Julia Gustafsson", "1");
        User user2 = new User("gustafsson@live.se", "123", "Julia Gustafsson", "2");
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkEmail() {
        User user = new User("Julia Gustafsson", "julia[at]live.se", "password", "12313");
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkPassword() {
        User user = new User("Julia Gustafsson", "julia@live.se", "passwrd", "12311");
    }

}