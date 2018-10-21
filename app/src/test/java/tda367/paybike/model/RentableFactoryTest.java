package tda367.paybike.model;


import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;

public class RentableFactoryTest {

    RentableFactory rf = new RentableFactory();

    @Test
    public void TestCreateRentable(){
        RentableFactory rf = new RentableFactory();
        Rentable testBike1 = rf.createTestRentable("Bike");
        assertNotNull(testBike1);
    }
}
