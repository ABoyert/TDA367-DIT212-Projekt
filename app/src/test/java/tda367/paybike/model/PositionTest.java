package tda367.paybike.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkZipcode() {
        Position position = new Position("Testgatan 2", 534313, "Göteborg");
    }


    @Test(expected = IllegalArgumentException.class)
    public void checkStreet() {
        Position position = new Position("", 534313, "Göteborg");
    }


    @Test(expected = IllegalArgumentException.class)
    public void checkCity() {
        Position position = new Position("Testgatan 2", 53431, "");
    }

    public void checkPosition() {
        Position position = new Position("Testgatan 2", 53431, "Göteborg");
        assertNotNull(position);
    }

}
