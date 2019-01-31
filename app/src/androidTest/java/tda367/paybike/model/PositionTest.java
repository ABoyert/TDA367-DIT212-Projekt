package tda367.paybike.model;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PositionTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tda367.paybike", appContext.getPackageName());

    }

    @Test public void createPosTest(){

        String tStreet = "TestStreet";
        int tZipInt = 21111;
        String tCity = "TestCity";

        Position tPos = new Position(tStreet, tZipInt, tCity);

        assert tPos.getStreet().equals(tStreet) && tPos.getZipCode() == tZipInt && tPos.getCity().equals(tCity);
    }

    @Test public void testGetSet(){

        //Other gets tested in previous test
        String tStreet = "TestStreet";
        int tZipInt = 21111;
        String tCity = "TestCity";

        Position tPos = new Position(tStreet, tZipInt, tCity);

        assert tPos.getCountry().equals("Sweden");

    }

    @Test public void testToString(){

        String tStreet = "TestStreet";
        int tZipInt = 21111;
        String tCity = "TestCity";

        Position tPos = new Position(tStreet, tZipInt, tCity);

        String tString = tStreet + "," + tZipInt + "," + tCity;

        assert tPos.toString().equals(tString);

    }

    @Test public void testParse(){

        String tStreet = "TestStreet";
        int tZipInt = 21111;
        String tCity = "TestCity";

        Position tPos = new Position(tStreet, tZipInt, tCity);

        String toParse = tStreet+","+tZipInt+","+tCity;

        Position tPos2 = Position.parseString(toParse);

        assert tPos2.equals(tPos);

    }

}