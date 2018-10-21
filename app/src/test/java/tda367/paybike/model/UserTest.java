package tda367.paybike.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/* Created by Julia Gustafsson
 *
 * Contains JUnit tests for User
 */
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