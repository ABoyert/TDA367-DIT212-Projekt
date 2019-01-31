package tda367.paybike.model;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RentableFactoryTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tda367.paybike", appContext.getPackageName());
    }

    @Test public void createTestBike(){
        RentableFactory rf = new RentableFactory();
        Position testPos = new Position("TestStreet",21111,"TestCity");
        Rentable testBike1 = rf.createRentable("Bike", "testBike", 100.0, testPos, true, "Per", null, "Bra cykel","01");

        List<Rentable> rentables =  new ArrayList<Rentable>();
        rentables.add(testBike1);
        assert testBike1.getId() == "01" && testBike1.getPosition() == testPos
                && testBike1.getPrice() == 100.0 && testBike1.isAvailable();
        assert rentables.get(0)==testBike1;
    }

    @Test public void createNoIDTestBike(){
        RentableFactory rf = new RentableFactory();
        Position testPos = new Position("TestStreet",21111,"TestCity");
        Rentable testBike1 = rf.createRentableNoID("Bike", "testBike", 100.0, testPos, true, "Per", null, "Bra cykel");
        assert testBike1.getName().equals("testBike") && testBike1.getPrice()==100 && testBike1.getId() == null;

    }

    @Test public void testCreateTestBike(){
        RentableFactory rf = new RentableFactory();

        //"Test", 25.00, new Position("Testgatan 1", 53422, "Vara"), true, "testOwner", null, "this is a test bike", "123test");

        Position tPos = new Position("Testgatan 1", 53422, "Vara");

        Rentable tBike = rf.createTestRentable("Bike");

        assert tBike.getName().equals("Test") && tBike.getPrice() == 25.00 && tBike.getPosition().equals(tPos) && tBike.getId().equals("123test");
    }
    /*
    public void createBikeWithFactoryTest() {
        RentableFactory rf = new RentableFactory();
        Position testPos = new Position("123","456","789",000);
        Rentable testBike1 = rf.createRentable("Bike", "123", 100.0, testPos, true, "Per", "www.chalmers.se", "Bra cykel");

        List<Rentable> rentables =  new ArrayList<Rentable>();
        rentables.add(testBike1);
        assert testBike1.getId() == "123" && testBike1.getPosition() == testPos
                && testBike1.getPrice() == 100.0 && testBike1.isAvailable();
    }*/


}