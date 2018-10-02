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
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tda367.paybike", appContext.getPackageName());
    }

    @Test
    public void createUserTest(){
        Position pos = new Position("Horsalevagen", "goteborg", "Swe", 42313);
        User testUser = new User("boy", "123", "456", pos);
        testUser.setId("999");
        testUser.setName("abc");
        testUser.setPassWord("password");

        assert testUser.getId() == "999" && testUser.getName() == "abc" && testUser.getPassWord() == "password";
    }


}
