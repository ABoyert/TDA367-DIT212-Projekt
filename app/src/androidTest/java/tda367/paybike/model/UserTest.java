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

    @Test(expected = IllegalArgumentException.class)
    public void checkEmail() {
        User user = new User("Julia Gustafsson", "julia[at]live.se", "password", "12313");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkPassword() {
        User user = new User("Julia Gustafsson", "julia@live.se", "passwrd", "12311");
    }

    @Test
    public void createUsersTest(){
        User testUser = new User("tester@test", "tester", "01");
        assert testUser.getName().equals("tester") && testUser.getEmail().equals("tester@test") && testUser.getUserID().equals("01");

        User testUserPassword = new User("tester2@test", "pass123","tester2", "02");
        assert testUserPassword.getName().equals("tester2") && testUser.getEmail().equals("tester2@test")
                && testUser.getUserID().equals("02") &&
                testUserPassword.getPassword().equals("pass123");
    }

@Test public void testEquals(){
    User testUser = new User("tester@test", "tester", "01");
    User testUser2 = new User("tester@test", "tester", "01");
    assert  testUser.equals(testUser2);
}

@Test public void testToString(){
    User testUser = new User("tester@test", "tester", "01");
    String string = "User Id: " + testUser.getUserID() + "\nName: " + testUser.getName() + "\nEmail: " + testUser.getEmail() + "\nPassword: " + testUser.getPassword();
    assert testUser.toString().equals(string);
}

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tda367.paybike", appContext.getPackageName());
    }

}