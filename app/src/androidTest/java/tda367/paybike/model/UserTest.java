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
        User user = new User("Julia Gustafsson", "julia[at]live.se", "password", "12313");
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkPassword() {
        User user = new User("Julia Gustafsson", "julia@live.se", "passwrd", "12311");
    }

    /*@Test
    public void createUserTest(){
        Position pos = new Position("Horsalevagen", "goteborg", "Swe", 42313);
        User testUser = new User("boy", "123", "456", pos);
        testUser.setId("999");
        testUser.setPassWord("password");
        testUser.setName("abc");

        assert testUser.getId() == "999" && testUser.getName() == "abc" && testUser.getPassWord() == "password";
    }*/


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tda367.paybike", appContext.getPackageName());
    }

    //Test for getters
    @Test
    public void testGetEmail() {
        User user = new User("test@test.com", "password", "Anton", "123123123");
        assertEquals(user.getEmail(), "test@test.com");
    }

    @Test
    public void testGetPassword() {
        User user = new User("test@test.com", "password", "Anton", "123123123");
        assertEquals(user.getPassword(), "password");
    }

    @Test
    public void testGetName() {
        User user = new User("test@test.com", "password", "Anton", "123123123");
        assertEquals(user.getName(), "Anton");
    }

    @Test
    public void testGetUserID() {
        User user = new User("test@test.com", "password", "Anton", "123123123");
        assertEquals(user.getUserID(), "123123123");
    }
}