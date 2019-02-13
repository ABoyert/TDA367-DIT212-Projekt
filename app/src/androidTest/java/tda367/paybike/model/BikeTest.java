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

    @Test public void createTestBike(){

        //With ID
        String bName = "Bike";
        Double bPrice = 20.00;
        Position bPos = new Position("TestStreet", 21111,"TestCity");
        boolean bBool = true;
        String bOwn = "TestOwner";
        String bDesc = "A Test Bike";
        String bID = "01";

        Bike testBike = new Bike(bName, bPrice,bPos, bBool,bOwn, null,bDesc,bID);
        assert testBike.getName() == bName && bPos == testBike.getPosition() && testBike.getId() == bID;

        //Without ID
        Bike testBike2 = new Bike(bName, bPrice, bPos, bBool, bOwn, null, bDesc);

        assert testBike2.getName().equals(bName) && bPos == testBike2.getPosition() && bOwn.equals(testBike2.getOwner());
    }

    @Test public void testGetSetAvailable(){
        Bike test = quickTestBike();

        String testname = "Test1";
        test.setName(testname);

        assert test.getName().equals(testname);

        boolean bTest = false;
        test.setAvailable(bTest);

        assert !test.isAvailable();
    }

    @Test public void testEquals(){
        Bike test = quickTestBike();
        Bike test2 = quickTestBike();

        assert test.equals(test2);
    }

    @Test public void testToString(){

        Bike test = quickTestBike();

        String testString = "Bike Id: " + test.getId() + "\nName: " + test.getName() + "\nPosition: " + test.getPosition() +
                "\nOwner Id: " + test.getOwner() + "\nDescription: " + test.getDescription();

        assert test.toString().equals(testString);
    }

    public Bike quickTestBike(){

        String bName = "Bike";
        Double bPrice = 20.00;
        Position bPos = new Position("TestStreet", 21111,"TestCity");
        boolean bBool = true;
        String bOwn = "TestOwner";
        String bDesc = "A Test Bike";
        String bID = "01";

        Bike testBike = new Bike(bName, bPrice,bPos, bBool,bOwn, null,bDesc,bID);

        return testBike;
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
