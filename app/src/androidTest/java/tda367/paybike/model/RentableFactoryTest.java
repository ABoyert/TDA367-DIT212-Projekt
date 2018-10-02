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

    public void createBikeWithFactoryTest() {
        RentableFactory rf = new RentableFactory();
        String testPos = "Testgatan 2";
        Rentable testBike1 = rf.createRentable("Bike", "123", 100.0, testPos);

        List<Rentable> rentables =  new ArrayList<Rentable>();
        rentables.add(testBike1);
        assert testBike1.getId() == "123" && testBike1.getPosition() == testPos
                && testBike1.getPrice() == 100.0 && testBike1.isAvailable();
    }


}