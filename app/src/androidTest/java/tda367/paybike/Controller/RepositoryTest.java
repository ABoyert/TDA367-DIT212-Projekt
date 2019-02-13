package tda367.paybike.Controller;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;


import tda367.paybike.model.Rentable;
import tda367.paybike.model.RentableFactory;
import tda367.paybike.repositori.Repository;
/**
 * Created by Oscar Orava Kilberg on 2018-10-04.
 */
@RunWith(AndroidJUnit4.class)
public class RepositoryTest {

    //Context appContext = InstrumentationRegistry.getTargetContext();
    //assertEquals("tda367.paybike", appContext.getPackageName());
    @Test
    public void getDatabaseRentables() {
    }

    @Test
    public void updateModelRentables() {
        Repository c = new Repository();


        assert c.getModelRentables().isEmpty();

        List<Rentable> testList = new ArrayList<>();
        testList.add(RentableFactory.createTestRentable("Bike"));

        c.getPayBike().setModelRentables(testList);
        assert !c.getModelRentables().isEmpty();
    }

    @Test
    public void getModelRentables() {
        Repository c = new Repository();
        assert c.getModelRentables().isEmpty();
    }

    @Test
    public void updateAndGetRentables() {
    }

    @Test
    public void getModel() {
    }

    @Test
    public void newRentable() {
    }

}