package tda367.paybike.handlers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.model.Bike;

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
    public void readFromListTest(){
       BikeHandler bh = new BikeHandler();
       Bike testBike1 = new Bike();
       Bike testBike2 = new Bike();
       Bike testBike3 = new Bike();

   }

   @Test
    public void readAllBikes(){
        BikeHandler bh = new BikeHandler();
        //bh.bikes
        //List<Bike> bikes = bh.getAllBikes;

   }

}
