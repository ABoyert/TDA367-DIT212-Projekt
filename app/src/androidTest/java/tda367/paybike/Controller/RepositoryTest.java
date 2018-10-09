package tda367.paybike.Controller;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import tda367.paybike.Repository.Repository;
import tda367.paybike.model.Rentable;
import tda367.paybike.model.RentableFactory;

/**
 * Created by Oscar Orava Kilberg on 2018-10-04.
 */
@RunWith(AndroidJUnit4.class)
public class RepositoryTest {

    //Context appContext = InstrumentationRegistry.getTargetContext();
    //assertEquals("tda367.paybike", appContext.getPackageName());
    @Test
    public void getDatabaseRentables() throws Exception {
    }

    @Test
    public void updateModelRentables() throws Exception {
        Repository c = new Repository();


        assert c.getModelRentables().isEmpty();

        List<Rentable> testList = new ArrayList<>();
        testList.add(RentableFactory.createTestRentable("Bike"));

        c.getPayBike().setModelRentables(testList);
        assert !c.getModelRentables().isEmpty();
    }

    @Test
    public void getModelRentables() throws Exception {
        Repository c = new Repository();
        assert c.getModelRentables().isEmpty();
    }

    @Test
    public void updateAndGetRentables() throws Exception {
    }

    @Test
    public void getModel() throws Exception {
    }

    @Test
    public void newRentable() throws Exception {
    }

}