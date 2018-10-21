package tda367.paybike.model;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BikeTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tda367.paybike", appContext.getPackageName());
    }
/*
        @Test
        public void createTestBike(){
            Bike testBike = new Bike();
            String testID = "1";
            String position[] = new String[]{"Testgatan 1"};
            Double testPrice = 25.00;
            testBike.setId(testID);
            testBike.setPosition(position);
            testBike.setPrice(testPrice);
            testBike.setAvailable(true);

            assert testBike.getId() == testID && testBike.getPosition() == position
                    && testBike.getPrice() == testPrice && testBike.isAvailable();
        }*/

}
