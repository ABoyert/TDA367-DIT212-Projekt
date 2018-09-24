package tda367.paybike.handlers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

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
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tda367.paybike", appContext.getPackageName());
    }

   @Test
    public void createNewWithFactoryTest(){
       BikeHandler bh = new BikeHandler();
       RentableFactory rf = new RentableFactory();
       String testPos = "Testgatan 2";
       Rentable testBike1 = rf.createRentable("Bike", "123", 100.0, testPos);

       List<Rentable> rentables =  new ArrayList<Rentable>();
       rentables.add(testBike1);
       assert testBike1.getId() == "123" && testBike1.getPosition() == testPos
               && testBike1.getPrice() == 100.0 && testBike1.isAvailable();
   }

    @Test
    public void readBikePropertiesFromListTest(){
        String testPos = "Testgatan 2";
        Bike testBike = new Bike("bike1", 25, testPos);
        ArrayList<Bike> bikes =  new ArrayList<>();
        bikes.add(testBike);

        assert bikes.get(1).getId() == "123";
    }
    /*
   @Test
    public void readBikeFromDatabaseTest(){
        BikeHandler bh = new BikeHandler();
        //bh.bikes
        //List<Bike> bikes = bh.getAllBikes;

   }*/

}
