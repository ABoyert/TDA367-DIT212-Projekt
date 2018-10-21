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

    @Test
    public void checkPosition() {
        Position position = new Position("Testgatan 2", 53431, "Göteborg");
        assertNotNull(position);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkParseStringIllegalArguments() {
        Position pos2 = Position.parseString("Testgatan 2:53431:Göteborg");
    }

    @Test
    public void checkParseString() {
        Position pos1 = new Position("Testgatan 2", 53431, "Göteborg");
        Position pos2 = Position.parseString("Testgatan 2,53431,Göteborg");
        assertEquals(pos1, pos2);
    }

    @Test
    public void checkToString() {
        Position pos1 = new Position("Testgatan 2", 53431, "Göteborg");
        String positionString = pos1.toString();
        Position pos2 = Position.parseString(positionString);
        assertEquals(pos1, pos2);
    }


    //Test for getters
    @Test
    public void testGetEmail() {
        Position position = new Position("Testgatan 2", 53431, "Göteborg");
        assertEquals(position.getStreet(), "Testgatan 2");
    }

    @Test
    public void testGetZipCode() {
        Position position = new Position("Testgatan 2", 53431, "Göteborg");
        assert position.getZipCode() == 53431;
    }

    @Test
    public void testGetCity() {
        Position position = new Position("Testgatan 2", 53431, "Göteborg");
        assertEquals(position.getCity(), "Göteborg");
    }

    @Test
    public void testGetCountry() {
        Position position = new Position("Testgatan 2", 53431, "Göteborg");
        assertEquals(position.getCountry(), "Sweden");
    }

}
