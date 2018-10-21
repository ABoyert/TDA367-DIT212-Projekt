package tda367.paybike.model;

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
                LocalDateTime.of(2020, 6,5,23,0),
                10, Request.Answer.UNANSWERED);
        Request r2 = new Request("Julia", "Bike",
                LocalDateTime.of(2020, 6, 5, 22, 30),
                LocalDateTime.of(2020, 6,5,23,0),
                10, Request.Answer.UNANSWERED);
        assertTrue(r1.equals(r2));
    }
}
