package tda367.paybike.model;

import android.net.Uri;

import org.junit.Before;
import org.junit.Test;

public class BikeTest {

    private final String TEST_ID = "1342j4d78a";
    private final String TEST_NAME = "Skeppshult";
    private final Position TEST_POSITION = new Position("Testgatan 2", 57574, "Karlstad");
    private final String TEST_OWNER = "Kalle Anka";
    private final String TEST_DESCRIPTION = "Very nice bike with two wheels and a lock.";
    private final double TEST_PRICE = 55;
    private final boolean TEST_AVAILABLE = true;
    private final Uri TEST_URI = Uri.parse("Test Uri");


    @Test(expected = IllegalArgumentException.class)
    public void checkName() {
        Bike bike = new Bike ("", TEST_PRICE, TEST_POSITION, TEST_AVAILABLE, TEST_OWNER, TEST_URI, TEST_DESCRIPTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkName2() {
        Bike bike = new Bike ("This is a very long name that should not be accepted by the constructor. " +
                "Hopefully this will return an exception to notify the user that something is wrong.",
                TEST_PRICE, TEST_POSITION, TEST_AVAILABLE, TEST_OWNER, TEST_URI, TEST_DESCRIPTION);
    }
}
