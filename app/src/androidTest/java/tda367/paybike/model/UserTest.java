package tda367.paybike.model;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UserTest {

    @Test (expected = IllegalArgumentException.class)
    public void checkEmail() {
        User user = new User("Julia", "Gustafsson", "julia[at]live.se", "password");
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkPassword() {
        User user = new User("Julia", "Gustafsson", "julia@live.se", "passwrd");
    }
    
}
