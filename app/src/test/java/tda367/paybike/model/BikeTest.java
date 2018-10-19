package tda367.paybike.model;

import android.net.Uri;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BikeTest {

    private final static String TEST_ID = "1342j4d78a";
    private final static String TEST_NAME = "Skeppshult";
    private final static Position TEST_POSITION = new Position("Testgatan 2", 57574, "Karlstad");
    private final static String TEST_OWNER = "Kalle Anka";
    private final static String TEST_DESCRIPTION = "Very nice bike with two wheels and a lock.";
    private final static double TEST_PRICE = 55;
    private final static boolean TEST_AVAILABLE = true;
    private final static Uri TEST_URI = Uri.parse("www.image.com");

    private static Bike testBike;

    @BeforeClass
    public static void setUp() throws Exception {
        testBike = new Bike (TEST_NAME, TEST_PRICE, TEST_POSITION, TEST_AVAILABLE, TEST_OWNER, TEST_URI, TEST_DESCRIPTION, TEST_ID);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void checkName() {

    }*/

    @Test(expected = IllegalArgumentException.class)
    public void checkName2() {
        Bike bike = new Bike ("This is a very long name that should not be accepted by the constructor. " +
                "Hopefully this will return an exception to notify the user that something is wrong.",
                TEST_PRICE, TEST_POSITION, TEST_AVAILABLE, TEST_OWNER, TEST_URI, TEST_DESCRIPTION);
    }

    @Test
    public void setAvailable() {
        testBike.setAvailable(false);
        assertEquals(false, testBike.isAvailable());
        testBike.setAvailable(TEST_AVAILABLE);
    }

    @Test
    public void isAvailable() {
        assertEquals(TEST_AVAILABLE, testBike.isAvailable());
    }

    @Test
    public void getName() {
        assertEquals(TEST_NAME, testBike.getName());
    }

    @Test
    public void setName() {
        testBike.setName("Bajk");
        assertEquals("Bajk", testBike.getName());
        testBike.setName(TEST_NAME);
    }

    @Test
    public void setOwner() {
        testBike.setOwner("Lars12345");
        assertEquals("Lars12345", testBike.getOwner());
        testBike.setOwner(TEST_OWNER);
    }

    @Test
    public void getOwner() {
        assertEquals(TEST_OWNER, testBike.getOwner());
    }

    @Test
    public void setId() {
        testBike.setId("12345");
        assertEquals("12345", testBike.getId());
        testBike.setId(TEST_ID);
    }

    @Test
    public void getId() {
        assertEquals(TEST_ID, testBike.getId());
    }

    @Test
    public void setPrice() {
        testBike.setPrice(22.0);
        assert(22.0 == testBike.getPrice());
        testBike.setPrice(TEST_PRICE);
    }

    @Test
    public void getPrice() {
        assert(TEST_PRICE == testBike.getPrice());
    }

    @Test
    public void setPosition() {
        Position newPos = new Position("Fiske", 86532,"Umeå");

        testBike.setPosition(newPos);
        assertEquals(newPos, testBike.getPosition());
        testBike.setPosition(TEST_POSITION);
    }

    @Test
    public void getPosition() {
        assertEquals(TEST_POSITION, testBike.getPosition());
    }

    @Test
    public void getImagePath() {
        assertEquals(TEST_URI, testBike.getImagePath());
    }

    @Test
    public void setImagePath() {
        Uri newUri = Uri.parse("www.abc.com");

        testBike.setImagePath(newUri);
        assertEquals(newUri, testBike.getImagePath());
        testBike.setImagePath(TEST_URI);
    }

    @Test
    public void getDescription() {
        assertEquals(TEST_DESCRIPTION, testBike.getDescription());
    }

    @Test
    public void setDescription() {
        testBike.setDescription("abcde");
        assertEquals("abcde", testBike.getDescription());
        testBike.setDescription(TEST_DESCRIPTION);
    }
}
