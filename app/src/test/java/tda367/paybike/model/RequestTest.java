package tda367.paybike.model;

import android.net.Uri;

import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.*;

/* Created by Julia Gustafsson
 *
 * Contains JUnit tests for Request
 */

public class RequestTest {

    /*@Test(expected = IllegalArgumentException.class)
    public void checkDates() {
        Request request = new Request("Julia", "Bike",
                LocalDateTime.of(2019, 1, 4, 10, 10),
                LocalDateTime.of(2018, 4, 2, 10, 30),
                10, Request.Answer.UNANSWERED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkTime() {
        Request request = new Request("Julia", "Bike",
                LocalDateTime.of(2019, 1, 4, 10, 10),
                LocalDateTime.of(2019, 1, 4, 8, 30),
                10, Request.Answer.UNANSWERED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkBeforeNow() {
        Request request = new Request("Julia", "Bike",
                LocalDateTime.of(2017, 1, 4, 10, 10),
                LocalDateTime.of(2017, 1, 4, 9, 30),
                10, Request.Answer.UNANSWERED);
    }*/

    @Test
    public void addRequest() {
        Request request = new Request("Julia", "Bike",
                LocalDateTime.of(2020, 1, 4, 10, 10),
                LocalDateTime.of(2020, 1, 5, 12, 30),
                10, Request.Answer.UNANSWERED);
        assertNotNull(request);
    }

    @Test
    public void equals() {
        Request r1 = new Request("Julia", "Bike",
                LocalDateTime.of(2020, 6, 5, 22, 30),
                LocalDateTime.of(2020, 6, 5, 23, 0),
                10, Request.Answer.UNANSWERED);
        Request r2 = new Request("Julia", "Bike",
                LocalDateTime.of(2020, 6, 5, 22, 30),
                LocalDateTime.of(2020, 6, 5, 23, 0),
                10, Request.Answer.UNANSWERED);
        assertTrue(r1.equals(r2));
    }

    @Test
    public void testAnswer(){
        User user = new User("oscar@test.com","Oscar" , "1");

        String name = user.getName()+"'s Bike";
        double price = 25.00;
        Position position = new Position("Testlane", 33333, "Testia");
        boolean available = true;
        String owner = user.getName();
        Uri imagePath = null;
        String description = "TestBike for request, owned by "+user.getName();
        Rentable testRentable = new Bike(name, price,position, available, owner, imagePath,description);

        Request testReq = new Request("SendingUser", testRentable.getId(),
                LocalDateTime.of(2020, 6, 5, 22, 30),
                LocalDateTime.of(2020, 6, 5, 23, 0),
                testRentable.getPrice(), Request.Answer.UNANSWERED);
        assertEquals(testReq.getAnswer(), Request.Answer.UNANSWERED);
        testReq.setAnswer(Request.Answer.ACCEPTED);
        assertEquals(testReq.getAnswer(), Request.Answer.ACCEPTED);
        testReq.setAnswer(Request.Answer.DENIED);
        assertEquals(testReq.getAnswer(), Request.Answer.DENIED);
    }

}
