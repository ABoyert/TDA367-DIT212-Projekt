package tda367.paybike.handlers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.model.Bike;
import tda367.paybike.model.Rentable;
import tda367.paybike.model.RentableFactory;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BikeHandlerTest {
    /*@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tda367.paybike", appContext.getPackageName());
    }

   @Test
    public void createNewWithFactoryTest(){     //Testar så att "bikeHandler" kan använda "RentableFactory"
       BikeHandler bh = new BikeHandler();
       RentableFactory rf = new RentableFactory();
       String testPos = "Testgatan 2";
       Rentable testBike1 = rf.createRentable("Bike", "123", 100.0, testPos, true, "", "", "");

       List<Rentable> rentables =  new ArrayList<Rentable>();
       rentables.add(testBike1);
       assert testBike1.getId() == "123" && testBike1.getPosition() == testPos
               && testBike1.getPrice() == 100.0 && testBike1.isAvailable();
   }

    @Test
    public void readBikePropertiesFromListTest(){
        String testPos = "Testgatan 2";
        Bike testBike = new Bike("bike1", 25, testPos,true, "", "", "");
        ArrayList<Bike> bikes =  new ArrayList<>();
        bikes.add(testBike);

        assert bikes.get(1).getId() == "123";


    }*/

   /*@Test
    public void readBikeFromDatabaseTest(){     //Testar om fuktionen "getAllBikes" får någon data
        BikeHandler bh = new BikeHandler();

        List<Bike> bikes = bh.getAllBikes();

       assert bikes.isEmpty() == false;
   }*/

    private static BikeHandler bh;
    private static Bike testBike;

    @BeforeClass
    public static void beforeClass() {
        bh = new BikeHandler();
        testBike = new Bike("testbike200", 10, "Test Street 4", true, "Per", "www.chalmers.se", "Bra cykel");
    }

    @Before
    public void beforeTests() {

    }

    @After
    public void afterClass() {
        bh.deleteBike(testBike);
    }

    @Test
    public void testAddBike() {
        bh.addBike(testBike);

        Bike dbBike = null;

        // Check if bike got added to db
        for (Bike b : bh.getAllBikes()) {
            if (b.getPosition().equals(testBike.getPosition())) {
                dbBike = b;
                testBike.setId(b.getId());
            }
        }

        assertEquals(testBike.getPosition(), dbBike.getPosition());
    }

    @Test
    public void testDeleteBike() {
        // Add bike to delete
        bh.addBike(testBike);

        // Get added bike
        for (Bike b : bh.getAllBikes()) {
            if (b.getPosition().equals(testBike.getPosition())) {
                testBike.setId(b.getId());
            }
        }

        // Delete bike
        bh.deleteBike(testBike);

        // Check if bike got deleted
        Bike dbBike = null;
        for (Bike b : bh.getAllBikes()) {
            if (b.getPosition().equals(testBike.getPosition())) {
                dbBike = b;
            }
        }

        assertTrue(dbBike == null);
    }

}
